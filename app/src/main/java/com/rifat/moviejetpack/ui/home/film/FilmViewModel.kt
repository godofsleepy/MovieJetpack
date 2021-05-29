package com.rifat.moviejetpack.ui.home.film

import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rifat.moviejetpack.data.entities.MovieEntity

class FilmViewModel : ViewModel() {
    fun getMovies(data: String?): List<MovieEntity> {
        val gson = Gson()
        val listMovie = object : TypeToken<List<MovieEntity>>() {}.type

        val movies: List<MovieEntity> = gson.fromJson(data, listMovie)
        return movies
    }
}