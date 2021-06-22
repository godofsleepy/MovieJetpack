package com.rifat.moviejetpack.di

import android.content.Context
import com.rifat.moviejetpack.data.repository.MovieRepository
import com.rifat.moviejetpack.data.repository.SeriesRepository
import com.rifat.moviejetpack.data.source.locale.LocaleDataSource
import com.rifat.moviejetpack.data.source.locale.room.FavDatabase
import com.rifat.moviejetpack.data.source.remote.RemoteDataSource

object Injection {
    fun provideMovieRepository(context: Context): MovieRepository {
        val database = FavDatabase.getInstance(context)
        val remoteRepository = RemoteDataSource.getInstance()
        val localDataSource = LocaleDataSource.getInstance(database.favDao())
        return MovieRepository.getInstance(remoteRepository, localDataSource)
    }

    fun provideSeriesRepository(context: Context): SeriesRepository {
        val database = FavDatabase.getInstance(context)
        val localDataSource = LocaleDataSource.getInstance(database.favDao())
        val remoteRepository = RemoteDataSource.getInstance()

        return SeriesRepository.getInstance(remoteRepository, localDataSource)
    }
}