package com.rifat.moviejetpack.ui.home.film

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rifat.moviejetpack.data.MovieRepository
import com.rifat.moviejetpack.data.entities.GenreEntity
import com.rifat.moviejetpack.data.entities.MovieEntity

class FilmViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    fun getMovies(): LiveData<List<MovieEntity>> = movieRepository.getListMovie()
    fun getGenres(): LiveData<List<GenreEntity>> = movieRepository.getMovieGenre()
}