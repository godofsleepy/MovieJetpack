package com.rifat.moviejetpack.ui.home.film

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rifat.moviejetpack.data.repository.MovieRepository
import com.rifat.moviejetpack.data.source.remote.responses.GenreResponse
import com.rifat.moviejetpack.data.source.remote.responses.MovieResponse

class FilmViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    fun getMovies(): LiveData<List<MovieResponse>> = movieRepository.getListMovie()
    fun getGenres(): LiveData<List<GenreResponse>> = movieRepository.getMovieGenre()
}