package com.rifat.moviejetpack.data.source.locale.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.rifat.moviejetpack.data.source.locale.entities.FavEntity

@Dao
interface FavDao {
    @Query("SELECT * FROM faventity")
    fun getCourses(): LiveData<List<FavEntity>>
}