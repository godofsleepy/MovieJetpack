package com.rifat.moviejetpack.data.source.remote.responses

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovieBaseResponse(
    @SerializedName("page")
    var page : Int? = 0,
    @SerializedName("results")
    var results: List<MovieResponse>? = emptyList(),
    @SerializedName("total_pages")
    var total_pages: Int? = 0
)

data class MovieResponse(
    @SerializedName("id")
    var id: Int,
    @SerializedName("title")
    var title: String,
    @SerializedName("overview")
    var overview: String,
    @SerializedName("original_language")
    var original_language: String,
    @SerializedName("poster_path")
    var poster_path: String,
    @SerializedName("backdrop_path")
    var backdrop_path: String,
    @SerializedName("release_date")
    var release_date: String,
    @SerializedName("vote_average")
    var vote_average: Double,
    @SerializedName("adult")
    var adult: Boolean = false
) : Serializable
