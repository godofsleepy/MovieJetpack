package com.rifat.moviejetpack.data.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SeriesEntity(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("overview")
    var overview: String,
    @SerializedName("original_language")
    var original_language: String,
    @SerializedName("poster_path")
    var poster_path: String,
    @SerializedName("first_air_date")
    var first_air_date: String,
    @SerializedName("vote_average")
    var vote_average: Double,
    @SerializedName("adult")
    var adult: Boolean = false
) : Serializable
