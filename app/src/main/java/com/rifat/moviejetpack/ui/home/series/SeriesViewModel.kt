package com.rifat.moviejetpack.ui.home.series

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rifat.moviejetpack.data.entities.SeriesEntity
import com.rifat.moviejetpack.utils.getJsonDataFromAsset

class SeriesViewModel : ViewModel() {
    fun getSeries(context: Context?): List<SeriesEntity> {
        if (context == null) return emptyList()

        val jsonFileString = getJsonDataFromAsset(context, "series.json")
        jsonFileString?.let { Log.i("data", it) }

        val gson = Gson()
        val listMovie = object : TypeToken<List<SeriesEntity>>() {}.type

        var movies: List<SeriesEntity> = gson.fromJson(jsonFileString, listMovie)
        return movies
    }
}