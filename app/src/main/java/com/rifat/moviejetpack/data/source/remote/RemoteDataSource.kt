package com.rifat.moviejetpack.data.source.remote

import com.rifat.moviejetpack.BuildConfig
import com.rifat.moviejetpack.data.entities.*
import com.rifat.moviejetpack.utils.EspressoIdlingResource
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
        EspressoIdlingResource.increment()
        getApiService().getListMovie(BuildConfig.MOVIEDB_ACCESS_KEY).await().results?.let {
            callback.onListMovieReceived(it)
            EspressoIdlingResource.decrement()
        }

    }

    suspend fun getListMovieByGenre(idGenre: String ,callback: LoadListMovieByGenre) {
        EspressoIdlingResource.increment()
        getApiService().getListMovieByGenre(BuildConfig.MOVIEDB_ACCESS_KEY, idGenre).await().results?.let {
            callback.onListMovieByGenreReceived(it)
            EspressoIdlingResource.decrement()
        }

    }

    suspend fun getMovieGenre(callback: LoadGenresCallback) {
        EspressoIdlingResource.increment()
        getApiService().getGenreMovie(BuildConfig.MOVIEDB_ACCESS_KEY).await().genres?.let {
            callback.onGenresReceived(it)
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getDetailMovie(id: String, callback: LoadDetailMovieCallback) {
        EspressoIdlingResource.increment()
        getApiService().getDetailMovie(id, BuildConfig.MOVIEDB_ACCESS_KEY).await().let {
            callback.onDetailMovieReceived(it)
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getListSeries(callback: LoadListSeriesCallback) {
        EspressoIdlingResource.increment()
        getApiService().getListSeries(BuildConfig.MOVIEDB_ACCESS_KEY).await().results?.let {
            callback.onListSeriesReceived(it)
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getSeriesGenre(callback: LoadGenresCallback) {
        EspressoIdlingResource.increment()
        getApiService().getGenreSeries(BuildConfig.MOVIEDB_ACCESS_KEY).await().genres?.let {
            callback.onGenresReceived(it)
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getDetailSeries(id: String, callback: LoadDetailSeriesCallback) {
        EspressoIdlingResource.increment()
        getApiService().getDetailSeries(id, BuildConfig.MOVIEDB_ACCESS_KEY).await().let {
            callback.onDetailSeriesReceived(it)
            EspressoIdlingResource.decrement()
        }
    }

    interface LoadListMovieCallback {
        fun onListMovieReceived(movieResponses: List<MovieEntity>)
    }

    interface LoadGenresCallback {
        fun onGenresReceived(genresReponse: List<GenreEntity>)
    }

    interface LoadDetailMovieCallback {
        fun onDetailMovieReceived(detailMovieEntity: DetailMovieEntity)
    }

    interface LoadListSeriesCallback {
        fun onListSeriesReceived(seriesResponse: List<SeriesEntity>)
    }

    interface LoadDetailSeriesCallback {
        fun onDetailSeriesReceived(detailMovieEntity: DetailSeriesEntity)
    }

    interface LoadListMovieByGenre {
        fun onListMovieByGenreReceived(movieResponses: List<MovieEntity>)
    }

}