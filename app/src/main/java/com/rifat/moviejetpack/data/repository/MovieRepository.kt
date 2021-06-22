package com.rifat.moviejetpack.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rifat.moviejetpack.data.source.locale.LocaleDataSource
import com.rifat.moviejetpack.data.source.locale.entities.FavEntity
import com.rifat.moviejetpack.data.source.remote.RemoteDataSource
import com.rifat.moviejetpack.data.source.remote.responses.DetailMovieResponse
import com.rifat.moviejetpack.data.source.remote.responses.GenreResponse
import com.rifat.moviejetpack.data.source.remote.responses.MovieResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

interface MovieDataSource {
    fun getListMovie(): LiveData<List<MovieResponse>>

    fun getDetailMovie(id: String): LiveData<DetailMovieResponse>

    fun getMovieGenre(): LiveData<List<GenreResponse>>

    fun getMovieByGenre(idGenre: String): LiveData<List<MovieResponse>>

    fun getAllFav(): LiveData<List<FavEntity>>

    fun addFav(movieResponse: DetailMovieResponse)
}

class MovieRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocaleDataSource,
) :
    MovieDataSource {

    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localDataSource: LocaleDataSource,
        ): MovieRepository =
            instance ?: synchronized(this) {
                MovieRepository(remoteData, localDataSource).apply { instance = this }
            }
    }

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
            remoteDataSource.getDetailMovie(id, object : RemoteDataSource.LoadDetailMovieCallback {
                override fun onDetailMovieReceived(detailMovieEntity: DetailMovieResponse) {
                    detailMovieResult.postValue(detailMovieEntity)
                }
            })
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

    override fun getAllFav(): LiveData<List<FavEntity>> {
        return localDataSource.getAllFav()
    }

    override fun addFav(movieResponse: DetailMovieResponse) {
        CoroutineScope(Dispatchers.IO).launch {
            val favEntity  = FavEntity(
                id = movieResponse.id,
                title = movieResponse.title,
                overview = movieResponse.overview,
                poster = movieResponse.poster_path,
                release_date = movieResponse.release_date,
                vote_average = movieResponse.vote_average,
                type = "movie"
            )
            Log.d("test", "Berhasil")
             localDataSource.insertFav(favEntity)
        }
    }

}