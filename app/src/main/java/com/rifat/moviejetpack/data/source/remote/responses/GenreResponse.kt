package com.rifat.moviejetpack.data.source.remote.responses

import com.google.gson.annotations.SerializedName

data class GenreBaseResponse(
    @SerializedName("genres")
    var genres: List<GenreResponse>? = emptyList(),
)

data class GenreResponse(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String
)

