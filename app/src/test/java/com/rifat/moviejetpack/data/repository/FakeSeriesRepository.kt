package com.rifat.moviejetpack.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rifat.moviejetpack.data.source.locale.entities.*
import com.rifat.moviejetpack.data.source.remote.RemoteDataSource
import com.rifat.moviejetpack.data.source.remote.responses.DetailSeriesResponse
import com.rifat.moviejetpack.data.source.remote.responses.GenreResponse
import com.rifat.moviejetpack.data.source.remote.responses.SeriesResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class FakeSeriesRepository(private val remoteDataSource: RemoteDataSource) :
    SeriesDataSource {


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