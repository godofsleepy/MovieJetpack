package com.rifat.moviejetpack.data.source.remote

import com.rifat.moviejetpack.data.entities.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    fun getListMovie(@Query("api_key") key: String): Call<MovieResponse>

    @GET("tv/popular")
    fun getListSeries(@Query("api_key") key: String): Call<SeriesResponse>

    @GET("movie/{id}")
    fun getDetailMovie(
        @Path("id") id: String,
        @Query("api_key") key: String
    ): Call<DetailMovieEntity>

    @GET("tv/{id}")
    fun getDetailSeries(
        @Path("id") id: String, @Query("api_key") key: String
    ): Call<DetailSeriesEntity>

    @GET("genre/movie/list")
    fun getGenreMovie(@Query("api_key") key: String): Call<GenreResponse>

    @GET("genre/tv/list")
    fun getGenreSeries(@Query("api_key") key: String): Call<GenreResponse>

    @GET("movie/popular")
    fun getListMovieByGenre(@Query("api_key") key: String, @Query("with_genres") genre: String): Call<MovieResponse>
}