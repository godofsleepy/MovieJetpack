package com.rifat.moviejetpack.data.source.remote

import com.rifat.moviejetpack.data.source.remote.responses.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    fun getListMovie(@Query("api_key") key: String): Call<MovieBaseResponse>

    @GET("tv/popular")
    fun getListSeries(@Query("api_key") key: String): Call<SeriesBaseResponse>

    @GET("movie/{id}")
    fun getDetailMovie(
        @Path("id") id: String,
        @Query("api_key") key: String
    ): Call<DetailMovieResponse>

    @GET("tv/{id}")
    fun getDetailSeries(
        @Path("id") id: String, @Query("api_key") key: String
    ): Call<DetailSeriesResponse>

    @GET("genre/movie/list")
    fun getGenreMovie(@Query("api_key") key: String): Call<GenreBaseResponse>

    @GET("genre/tv/list")
    fun getGenreSeries(@Query("api_key") key: String): Call<GenreBaseResponse>

    @GET("movie/popular")
    fun getListMovieByGenre(@Query("api_key") key: String, @Query("with_genres") genre: String): Call<MovieBaseResponse>

    @GET("tv/popular")
    fun getSeriesByGenre(@Query("api_key") key: String, @Query("with_genres") genre: String): Call<SeriesBaseResponse>
}