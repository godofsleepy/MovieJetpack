package com.rifat.moviejetpack.di

import android.content.Context
import com.rifat.moviejetpack.data.MovieRepository
import com.rifat.moviejetpack.data.source.remote.RemoteDataSource

object Injection {
    fun provideMovieRepository(): MovieRepository {

        val remoteRepository = RemoteDataSource.getInstance()

        return MovieRepository.getInstance(remoteRepository)
    }
}