package com.rifat.moviejetpack.ui.detail_film

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rifat.moviejetpack.data.MovieRepository
import com.rifat.moviejetpack.data.entities.DetailMovieEntity

class DetailFilmViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    fun getDetailFilm(id: Int): LiveData<DetailMovieEntity> = movieRepository.getDetailMovie(id.toString())
}