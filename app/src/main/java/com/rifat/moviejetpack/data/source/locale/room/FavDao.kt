package com.rifat.moviejetpack.data.source.locale.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rifat.moviejetpack.data.source.locale.entities.FavEntity

@Dao
interface FavDao {
    @Query("SELECT * FROM fav_entity")
    fun getFav(): LiveData<List<FavEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFav(favEntity: FavEntity)

}