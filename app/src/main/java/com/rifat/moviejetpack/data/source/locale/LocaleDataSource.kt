package com.rifat.moviejetpack.data.source.locale

import androidx.lifecycle.LiveData
import com.rifat.moviejetpack.data.source.locale.entities.FavEntity
import com.rifat.moviejetpack.data.source.locale.room.FavDao

class LocaleDataSource private constructor(private val mFavDao: FavDao) {
    companion object {
        private var INSTANCE: LocaleDataSource? = null

        fun getInstance(favDao: FavDao): LocaleDataSource =
            INSTANCE ?: LocaleDataSource(favDao).apply {
                INSTANCE = this
            }
    }

    fun getAllFav(): LiveData<List<FavEntity>> = mFavDao.getFav()

    fun insertFav(favEntity: FavEntity) = mFavDao.insertFav(favEntity)

    fun getFavById(id: String) = mFavDao.getDataById(id)

    fun deleteFavById(id: String) = mFavDao.deleteById(id)
}