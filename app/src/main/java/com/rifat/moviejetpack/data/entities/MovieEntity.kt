package com.rifat.moviejetpack.data.entities

import com.google.gson.annotations.SerializedName

data class MovieEntity(
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
    @SerializedName("release_date")
    var release_date: String,
    @SerializedName("vote_average")
    var vote_average: Double,
    @SerializedName("adult")
    var adult: Boolean = false
)
