package com.rifat.moviejetpack.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rifat.moviejetpack.data.source.locale.LocaleDataSource
import com.rifat.moviejetpack.data.source.locale.entities.*
import com.rifat.moviejetpack.data.source.remote.RemoteDataSource
import com.rifat.moviejetpack.data.source.remote.responses.DetailSeriesResponse
import com.rifat.moviejetpack.data.source.remote.responses.GenreResponse
import com.rifat.moviejetpack.data.source.remote.responses.SeriesResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class FakeSeriesRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocaleDataSource
) :
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

    override fun addFav(detailSeriesResponse: DetailSeriesResponse): LiveData<Map<String, Any>> {
        val favResult = MutableLiveData<Map<String, Any>>()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val favEntity = FavEntity(
                    id = "s-${detailSeriesResponse.id}",
                    title = detailSeriesResponse.name,
                    overview = detailSeriesResponse.overview,
                    poster = detailSeriesResponse.poster_path,
                    release_date = detailSeriesResponse.first_air_date,
                    vote_average = detailSeriesResponse.vote_average,
                    detail = Gson().toJson(detailSeriesResponse)
                )
                localDataSource.insertFav(favEntity)
                favResult.postValue(mutableMapOf("status" to true, "message" to ""))
            } catch (e: Exception) {
                favResult.postValue(mutableMapOf("status" to false, "message" to ""))
            }
        }

        return favResult
    }

    override fun getFavById(id: String): LiveData<FavEntity> {
        return localDataSource.getFavById(id)
    }

    override fun deleteFavById(id: String): LiveData<Map<String, Any>> {
        val favResult = MutableLiveData<Map<String, Any>>()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                localDataSource.deleteFavById(id)
                favResult.postValue(mutableMapOf("status" to true, "message" to ""))
            } catch (e: Exception) {
                favResult.postValue(mutableMapOf("status" to false, "message" to ""))
            }
        }

        return favResult
    }

    override fun getDetailSeries(id: String): LiveData<DetailSeriesResponse> {
        val series = MutableLiveData<DetailSeriesResponse>()
        CoroutineScope(Dispatchers.IO).launch {
            val data = localDataSource.getFavById("s-$id").value
            if (data?.detail == null) {
                remoteDataSource.getDetailSeries(id,
                    object : RemoteDataSource.LoadDetailSeriesCallback {
                        override fun onDetailSeriesReceived(detailSeriesEntity: DetailSeriesResponse) {
                            series.postValue(detailSeriesEntity)
                        }
                    })
            } else {
                val gson = Gson()
                val type = object : TypeToken<DetailSeriesResponse>() {}.type

                val detail: DetailSeriesResponse = gson.fromJson(data.detail, type)
                series.postValue(detail)
            }
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