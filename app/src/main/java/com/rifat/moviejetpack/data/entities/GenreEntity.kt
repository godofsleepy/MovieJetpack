package com.rifat.moviejetpack.data.entities

import com.google.gson.annotations.SerializedName

data class GenreResponse(
    @SerializedName("genres")
    var genres: List<GenreEntity>? = emptyList(),
)

data class GenreEntity(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String
)

