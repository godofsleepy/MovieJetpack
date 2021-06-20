package com.rifat.moviejetpack.data.source.locale.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rifat.moviejetpack.data.source.locale.entities.FavEntity

@Database(entities = [FavEntity::class], version = 1, exportSchema = false)
abstract class FavDatabase : RoomDatabase() {
    abstract fun favDao(): FavDao

    companion object {

        @Volatile
        private var INSTANCE: FavDatabase? = null

        fun getInstance(context: Context): FavDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    FavDatabase::class.java,
                    "Fav.db"
                ).build().apply {
                    INSTANCE = this
                }
            }
    }
}