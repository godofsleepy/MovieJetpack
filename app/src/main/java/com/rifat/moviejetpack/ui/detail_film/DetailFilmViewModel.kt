package com.rifat.moviejetpack.ui.detail_film

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rifat.moviejetpack.data.repository.MovieRepository
import com.rifat.moviejetpack.data.source.remote.responses.DetailMovieResponse
import com.rifat.moviejetpack.data.source.remote.responses.MovieResponse

class DetailFilmViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    fun getDetailFilm(id: Int): LiveData<DetailMovieResponse> = movieRepository.getDetailMovie(id.toString())

    fun getRelatedMovie(idGenre: String) : LiveData<List<MovieResponse>> = movieRepository.getMovieByGenre(idGenre)

    fun addToFav(movieResponse: DetailMovieResponse) : LiveData<Map<String, Any>> = movieRepository.addFav(movieResponse)
}