package com.rifat.moviejetpack.ui.detail_film

import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rifat.moviejetpack.data.entities.MovieEntity

class DetailFilmViewModel : ViewModel() {

    fun getDetailFilm(id: Int, data: String?): MovieEntity {
        val emptyMovie = MovieEntity(0, "", "", "", "", "", "", 0.0, false)
        if (data.isNullOrBlank()) return emptyMovie
        val gson = Gson()
        val listType = object : TypeToken<List<MovieEntity>>() {}.type

        val listMovie: List<MovieEntity> = gson.fromJson(data, listType)
        return try {
            val movie = listMovie.first { it.id == id }
            movie
        } catch (e: NoSuchElementException) {
            emptyMovie
        }
    }
}