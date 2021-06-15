package com.rifat.moviejetpack.data.entities

import com.google.gson.annotations.SerializedName

data class DetailMovieEntity(
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
    var adult: Boolean = false,
    @SerializedName("genres")
    var genres: List<GenreEntity> = emptyList(),
    @SerializedName("production_companies")
    var productionCompany: List<ProductionCompany> = emptyList()
)

data class ProductionCompany(
    @SerializedName("logo_path")
    var logo: String,
    @SerializedName("name")
    var name: String,
)
