package com.rifat.moviejetpack.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rifat.moviejetpack.data.repository.MovieDataSource
import com.rifat.moviejetpack.data.source.remote.RemoteDataSource
import com.rifat.moviejetpack.data.source.remote.responses.DetailMovieResponse
import com.rifat.moviejetpack.data.source.remote.responses.GenreResponse
import com.rifat.moviejetpack.data.source.remote.responses.MovieResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FakeMovieRepository (private val remoteDataSource: RemoteDataSource) :
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

}