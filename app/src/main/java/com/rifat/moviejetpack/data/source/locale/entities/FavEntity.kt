package com.rifat.moviejetpack.data.source.locale.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav_entity")
data class FavEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: String,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "overview")
    var overview: String,
    @ColumnInfo(name = "poster_path")
    var poster: String,
    @ColumnInfo(name = "release_date")
    var release_date: String,
    @ColumnInfo(name = "vote_average")
    var vote_average: Double,
    @ColumnInfo(name = "detail")
    var detail: String
)
