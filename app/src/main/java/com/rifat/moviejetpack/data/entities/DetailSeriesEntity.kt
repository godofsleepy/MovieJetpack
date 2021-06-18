package com.rifat.moviejetpack.data.entities

import com.google.gson.annotations.SerializedName

data class DetailSeriesEntity(
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
    @SerializedName("genres")
    var genres: List<GenreEntity> = emptyList(),
    @SerializedName("backdrop_path")
    var backdrop_path: String,
    @SerializedName("first_air_date")
    var first_air_date: String,
    @SerializedName("vote_average")
    var vote_average: Double,
    @SerializedName("adult")
    var adult: Boolean = false,
    @SerializedName("homepage")
    var homepage: String = "",
    @SerializedName("production_companies")
    var productionCompany: List<ProductionCompany> = emptyList(),
    @SerializedName("tagline")
    var tagline: String = "MovieJetpack",
    @SerializedName("number_of_episodes")
    var episode: Int = 0,
    @SerializedName("number_of_seasons")
    var season: Int = 0,
    @SerializedName("networks")
    var networksList: List<Networks> = emptyList(),
    @SerializedName("seasons")
    var seasons: List<Season> = emptyList()
)

data class Networks(
    @SerializedName("name")
    var name: String = "",
    @SerializedName("logo_path")
    var logo: String = ""
)

data class Season(
    @SerializedName("air_date")
    var release: String = "",
    @SerializedName("episode_count")
    var episode: Int = 0,
    @SerializedName("name")
    var name: String = "",
    @SerializedName("overview")
    var overview: String = "",
    @SerializedName("poster_path")
    var poster_path: String = "",
    @SerializedName("season_number")
    var season: Int = 0
)
