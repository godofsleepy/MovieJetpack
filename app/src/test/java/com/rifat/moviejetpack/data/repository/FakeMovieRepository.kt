package com.rifat.moviejetpack.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rifat.moviejetpack.data.entities.DetailMovieEntity
import com.rifat.moviejetpack.data.entities.GenreEntity
import com.rifat.moviejetpack.data.entities.MovieEntity
import com.rifat.moviejetpack.data.source.remote.RemoteDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FakeMovieRepository (private val remoteDataSource: RemoteDataSource) :
    MovieDataSource {



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

    override fun getDetailMovie(id: String): LiveData<DetailMovieEntity> {
        val detailMovieResult = MutableLiveData<DetailMovieEntity>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getDetailMovie(id, object : RemoteDataSource.LoadDetailMovieCallback {
                override fun onDetailMovieReceived(detailMovieEntity: DetailMovieEntity) {
                    detailMovieResult.postValue(detailMovieEntity)
                }
            })
        }
        return detailMovieResult
    }

    override fun getMovieGenre(): LiveData<List<GenreEntity>> {
        val genreResult = MutableLiveData<List<GenreEntity>>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getMovieGenre(object : RemoteDataSource.LoadGenresCallback {
                override fun onGenresReceived(genresReponse: List<GenreEntity>) {
                    genreResult.postValue(genresReponse)
                }
            })
        }

        return genreResult
    }

}