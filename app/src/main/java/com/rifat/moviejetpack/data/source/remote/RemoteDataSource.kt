package com.rifat.moviejetpack.data.source.remote

import com.rifat.moviejetpack.BuildConfig
import com.rifat.moviejetpack.data.entities.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.await
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }

        fun getApiService(): ApiService {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }

    suspend fun getListMovie(callback: LoadListMovieCallback) {
        getApiService().getListMovie(BuildConfig.MOVIEDB_ACCESS_KEY).await().results?.let {
            callback.onListMovieReceived(it)
        }
    }

    suspend fun getMovieGenre(callback:LoadGenresCallback ) {
        getApiService().getGenreMovie(BuildConfig.MOVIEDB_ACCESS_KEY).await().genres?.let {
            callback.onGenresReceived(it)
        }
    }

    suspend fun getDetailMovie(id: String ,callback:LoadDetailMovieCallback) {
        getApiService().getDetailMovie( id, BuildConfig.MOVIEDB_ACCESS_KEY).await().let {
            callback.onDetailMovieReceived(it)
        }
    }


    suspend fun getListSeries(callback: LoadListSeriesCallback) {
        getApiService().getListSeries(BuildConfig.MOVIEDB_ACCESS_KEY).await().results?.let {
            callback.onListSeriesReceived(it)
        }
    }

    suspend fun getSeriesGenre(callback:LoadGenresCallback ) {
        getApiService().getGenreSeries(BuildConfig.MOVIEDB_ACCESS_KEY).await().genres?.let {
            callback.onGenresReceived(it)
        }
    }

    suspend fun getDetailSeries(id: String ,callback:LoadDetailSeriesCallback) {
        getApiService().getDetailSeries( id, BuildConfig.MOVIEDB_ACCESS_KEY).await().let {
            callback.onDetailSeriesReceived(it)
        }
    }

    interface LoadListMovieCallback {
        fun onListMovieReceived(movieResponses: List<MovieEntity>)
    }

    interface LoadGenresCallback {
        fun onGenresReceived(genresReponse : List<GenreEntity>)
    }

    interface  LoadDetailMovieCallback {
        fun onDetailMovieReceived(detailMovieEntity: DetailMovieEntity)
    }

    interface LoadListSeriesCallback {
        fun onListSeriesReceived(seriesResponse: List<SeriesEntity>)
    }

    interface  LoadDetailSeriesCallback {
        fun onDetailSeriesReceived(detailMovieEntity: DetailSeriesEntity)
    }

}