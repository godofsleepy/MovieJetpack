package com.rifat.moviejetpack.data.source.locale.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.rifat.moviejetpack.data.source.locale.entities.FavEntity

@Dao
interface FavDao {
    @Query("SELECT * FROM fav_entity")
    fun getFav(): LiveData<List<FavEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFav(favEntity: FavEntity)

    @Query("SELECT * FROM fav_entity WHERE id = :id")
    fun getDataById(id: String): LiveData<FavEntity>

    @Query("DELETE FROM FAV_ENTITY WHERE id = :id")
    fun deleteById(id: String)

}