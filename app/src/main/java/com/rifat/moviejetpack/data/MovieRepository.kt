package com.rifat.moviejetpack.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rifat.moviejetpack.data.entities.GenreEntity
import com.rifat.moviejetpack.data.entities.MovieEntity
import com.rifat.moviejetpack.data.source.remote.RemoteDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

interface MovieDataSource {
    fun getListMovie(): LiveData<List<MovieEntity>>

//    fun getDetailMovie(id: String): LiveData<List<CourseEntity>>

    fun getMovieGenre(): LiveData<List<GenreEntity>>
}

class MovieRepository private constructor(private val remoteDataSource: RemoteDataSource) :
    MovieDataSource {

    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(remoteData: RemoteDataSource): MovieRepository =
            instance ?: synchronized(this) {
                MovieRepository(remoteData).apply { instance = this }
            }
    }

    override fun getListMovie(): LiveData<List<MovieEntity>> {
        val movieResult = MutableLiveData<List<MovieEntity>>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getListMovie(object : RemoteDataSource.LoadListMovieCallback {
                override fun onListMovieReceived(movieResponses: List<MovieEntity>) {
                    movieResult.postValue(movieResponses)
                }
            })
        }

        return movieResult
    }

    override fun getMovieGenre(): LiveData<List<GenreEntity>> {
        val genreResult = MutableLiveData<List<GenreEntity>>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getMovieGenre(object : RemoteDataSource.LoadGenresCallback{
                override fun onGenresReceived(genresReponse: List<GenreEntity>) {
                    genreResult.postValue(genresReponse)
                }
            })
        }

        return genreResult
    }

}