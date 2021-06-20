package com.rifat.moviejetpack.data.source.remote.responses

import com.google.gson.annotations.SerializedName

data class DetailSeriesResponse(
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
    var genres: List<GenreResponse> = emptyList(),
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
    var productionCompany: List<ProductionCompanyResponse> = emptyList(),
    @SerializedName("tagline")
    var tagline: String = "MovieJetpack",
    @SerializedName("number_of_episodes")
    var episode: Int = 0,
    @SerializedName("number_of_seasons")
    var season: Int = 0,
    @SerializedName("networks")
    var networksList: List<NetworksResponse> = emptyList(),
    @SerializedName("seasons")
    var seasons: List<SeasonResponse> = emptyList()
)

data class NetworksResponse(
    @SerializedName("name")
    var name: String = "",
    @SerializedName("logo_path")
    var logo: String = ""
)

data class SeasonResponse(
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
)
