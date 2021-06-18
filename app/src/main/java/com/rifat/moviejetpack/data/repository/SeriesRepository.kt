package com.rifat.moviejetpack.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rifat.moviejetpack.data.entities.*
import com.rifat.moviejetpack.data.source.remote.RemoteDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

interface SeriesDataSource {
    fun getListSeries(): LiveData<List<SeriesEntity>>

    fun getDetailSeries(id: String): LiveData<DetailSeriesEntity>

    fun getSeriesGenre(): LiveData<List<GenreEntity>>

    fun getListSeries(idGenre: String): LiveData<List<SeriesEntity>>
}

class SeriesRepository private constructor(private val remoteDataSource: RemoteDataSource) :
    SeriesDataSource {

    companion object {
        @Volatile
        private var instance: SeriesRepository? = null

        fun getInstance(remoteData: RemoteDataSource): SeriesRepository =
            instance ?: synchronized(this) {
                SeriesRepository(remoteData).apply { instance = this }
            }
    }

    override fun getListSeries(): LiveData<List<SeriesEntity>> {
        val series = MutableLiveData<List<SeriesEntity>>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getListSeries(object : RemoteDataSource.LoadListSeriesCallback {
                override fun onListSeriesReceived(seriesResponse: List<SeriesEntity>) {
                    series.postValue(seriesResponse)
                }
            })
        }
        return series
    }

    override fun getListSeries(idGenre: String): LiveData<List<SeriesEntity>> {
        val series = MutableLiveData<List<SeriesEntity>>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getSeriesByGenre(idGenre,
                object : RemoteDataSource.LoadListSeriesCallback {
                    override fun onListSeriesReceived(seriesResponse: List<SeriesEntity>) {
                        series.postValue(seriesResponse)
                    }
                })
        }
        return series
    }

    override fun getDetailSeries(id: String): LiveData<DetailSeriesEntity> {
        val series = MutableLiveData<DetailSeriesEntity>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getDetailSeries(id,
                object : RemoteDataSource.LoadDetailSeriesCallback {
                    override fun onDetailSeriesReceived(detailSeriesEntity: DetailSeriesEntity) {
                        series.postValue(detailSeriesEntity)
                    }
                })
        }
        return series
    }

    override fun getSeriesGenre(): LiveData<List<GenreEntity>> {
        val genreResult = MutableLiveData<List<GenreEntity>>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getSeriesGenre(object : RemoteDataSource.LoadGenresCallback {
                override fun onGenresReceived(genresReponse: List<GenreEntity>) {
                    genreResult.postValue(genresReponse)
                }
            })
        }

        return genreResult
    }
}