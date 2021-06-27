package com.rifat.moviejetpack.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rifat.moviejetpack.data.repository.MovieDataSource
import com.rifat.moviejetpack.data.source.locale.LocaleDataSource
import com.rifat.moviejetpack.data.source.locale.entities.FavEntity
import com.rifat.moviejetpack.data.source.remote.RemoteDataSource
import com.rifat.moviejetpack.data.source.remote.responses.DetailMovieResponse
import com.rifat.moviejetpack.data.source.remote.responses.GenreResponse
import com.rifat.moviejetpack.data.source.remote.responses.MovieResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FakeMovieRepository (private val remoteDataSource: RemoteDataSource, private val localDataSource: LocaleDataSource) :
    MovieDataSource {


    override fun getListMovie(): LiveData<List<MovieResponse>> {
        val movieResult = MutableLiveData<List<MovieResponse>>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getListMovie(object : RemoteDataSource.LoadListMovieCallback {
                override fun onListMovieReceived(movieResponses: List<MovieResponse>) {
                    movieResult.postValue(movieResponses)
                }
            })
        }

        return movieResult
    }

    override fun getDetailMovie(id: String): LiveData<DetailMovieResponse> {
        val detailMovieResult = MutableLiveData<DetailMovieResponse>()
        CoroutineScope(Dispatchers.IO).launch {
            val data : FavEntity? = localDataSource.getFavById("m-$id").value
            if (data?.detail == null) {
                remoteDataSource.getDetailMovie(
                    id,
                    object : RemoteDataSource.LoadDetailMovieCallback {
                        override fun onDetailMovieReceived(detailMovieEntity: DetailMovieResponse) {
                            detailMovieResult.postValue(detailMovieEntity)
                        }
                    })
            } else {
                val gson = Gson()
                val type = object : TypeToken<DetailMovieResponse>() {}.type

                val detail: DetailMovieResponse = gson.fromJson(data.detail, type)
                detailMovieResult.postValue(detail)
            }
        }
        return detailMovieResult
    }

    override fun getMovieGenre(): LiveData<List<GenreResponse>> {
        val genreResult = MutableLiveData<List<GenreResponse>>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getMovieGenre(object : RemoteDataSource.LoadGenresCallback {
                override fun onGenresReceived(genresReponse: List<GenreResponse>) {
                    genreResult.postValue(genresReponse)
                }
            })
        }

        return genreResult
    }

    override fun getMovieByGenre(idGenre: String): LiveData<List<MovieResponse>> {
        val movieResult = MutableLiveData<List<MovieResponse>>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getListMovieByGenre(idGenre,
                object : RemoteDataSource.LoadListMovieByGenre {
                    override fun onListMovieByGenreReceived(movieResponses: List<MovieResponse>) {
                        movieResult.postValue(movieResponses)
                    }
                })
        }

        return movieResult
    }

    override fun getAllFav():  LiveData<PagedList<FavEntity>>{
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getAllFav(), config).build()
    }

    override fun addFav(movieResponse: DetailMovieResponse): LiveData<Map<String, Any>> {
        val favResult = MutableLiveData<Map<String, Any>>()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val favEntity = FavEntity(
                    id = "m-${movieResponse.id}",
                    title = movieResponse.title,
                    overview = movieResponse.overview,
                    poster = movieResponse.poster_path,
                    release_date = movieResponse.release_date,
                    vote_average = movieResponse.vote_average,
                    detail = Gson().toJson(movieResponse)
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

}