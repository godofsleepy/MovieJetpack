package com.rifat.moviejetpack.ui.home.film

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rifat.moviejetpack.data.entities.MovieEntity
import com.rifat.moviejetpack.utils.getJsonDataFromAsset

class FilmViewModel : ViewModel() {
    fun getMovies(context: Context?): List<MovieEntity> {
        if (context == null) return emptyList()

        val jsonFileString = getJsonDataFromAsset(context, "movies.json")
        jsonFileString?.let { Log.i("data", it) }

        val gson = Gson()
        val listMovie = object : TypeToken<List<MovieEntity>>() {}.type

        var movies: List<MovieEntity> = gson.fromJson(jsonFileString, listMovie)
        return movies
    }
}