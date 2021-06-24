package com.rifat.moviejetpack.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rifat.moviejetpack.data.source.locale.LocaleDataSource
import com.rifat.moviejetpack.data.source.locale.entities.FavEntity
import com.rifat.moviejetpack.data.source.remote.RemoteDataSource
import com.rifat.moviejetpack.data.source.remote.responses.DetailSeriesResponse
import com.rifat.moviejetpack.data.source.remote.responses.GenreResponse
import com.rifat.moviejetpack.data.source.remote.responses.SeriesResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

interface SeriesDataSource {
    fun getListSeries(): LiveData<List<SeriesResponse>>

    fun getDetailSeries(id: String): LiveData<DetailSeriesResponse>

    fun getSeriesGenre(): LiveData<List<GenreResponse>>

    fun getListSeriesByGenre(idGenre: String): LiveData<List<SeriesResponse>>

    fun addFav(detailSeriesResponse: DetailSeriesResponse)
}

class SeriesRepository private constructor(private val remoteDataSource: RemoteDataSource, private val localDataSource: LocaleDataSource,) :
    SeriesDataSource {

    companion object {
        @Volatile
        private var instance: SeriesRepository? = null

        fun getInstance(remoteData: RemoteDataSource, localDataSource: LocaleDataSource): SeriesRepository =
            instance ?: synchronized(this) {
                SeriesRepository(remoteData, localDataSource).apply { instance = this }
            }
    }

    override fun getListSeries(): LiveData<List<SeriesResponse>> {
        val series = MutableLiveData<List<SeriesResponse>>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getListSeries(object : RemoteDataSource.LoadListSeriesCallback {
                override fun onListSeriesReceived(seriesResponse: List<SeriesResponse>) {
                    series.postValue(seriesResponse)
                }
            })
        }
        return series
    }

    override fun getListSeriesByGenre(idGenre: String): LiveData<List<SeriesResponse>> {
        val series = MutableLiveData<List<SeriesResponse>>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getSeriesByGenre(idGenre,
                object : RemoteDataSource.LoadListSeriesCallback {
                    override fun onListSeriesReceived(seriesResponse: List<SeriesResponse>) {
                        series.postValue(seriesResponse)
                    }
                })
        }
        return series
    }

    override fun addFav(detailSeriesResponse: DetailSeriesResponse) {
        CoroutineScope(Dispatchers.IO).launch {
            val favEntity  = FavEntity(
                id = "s-${detailSeriesResponse.id}",
                title = detailSeriesResponse.name,
                overview = detailSeriesResponse.overview,
                poster = detailSeriesResponse.poster_path,
                release_date = detailSeriesResponse.first_air_date,
                vote_average = detailSeriesResponse.vote_average,

            )
            localDataSource.insertFav(favEntity)
        }
    }

    override fun getDetailSeries(id: String): LiveData<DetailSeriesResponse> {
        val series = MutableLiveData<DetailSeriesResponse>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getDetailSeries(id,
                object : RemoteDataSource.LoadDetailSeriesCallback {
                    override fun onDetailSeriesReceived(detailSeriesEntity: DetailSeriesResponse) {
                        series.postValue(detailSeriesEntity)
                    }
                })
        }
        return series
    }

    override fun getSeriesGenre(): LiveData<List<GenreResponse>> {
        val genreResult = MutableLiveData<List<GenreResponse>>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getSeriesGenre(object : RemoteDataSource.LoadGenresCallback {
                override fun onGenresReceived(genresReponse: List<GenreResponse>) {
                    genreResult.postValue(genresReponse)
                }
            })
        }

        return genreResult
    }
}