package com.rifat.moviejetpack.ui.home.series

import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rifat.moviejetpack.data.entities.SeriesEntity

class SeriesViewModel : ViewModel() {
    fun getSeries(data : String?): List<SeriesEntity> {

        val gson = Gson()
        val listMovie = object : TypeToken<List<SeriesEntity>>() {}.type

        val movies: List<SeriesEntity> = gson.fromJson(data, listMovie)
        return movies
    }

}