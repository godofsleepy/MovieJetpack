package com.rifat.moviejetpack.data.source.locale

import com.rifat.moviejetpack.data.source.locale.room.FavDao

class LocaleDataSource private constructor(private val mFavDao: FavDao) {
    companion object {
        private var INSTANCE: LocaleDataSource? = null

        fun getInstance(favDao: FavDao): LocaleDataSource =
            INSTANCE ?: LocaleDataSource(favDao).apply {
                INSTANCE = this
            }

    }
}