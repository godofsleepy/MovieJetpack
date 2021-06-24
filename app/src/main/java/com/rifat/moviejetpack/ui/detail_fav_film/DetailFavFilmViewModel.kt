package com.rifat.moviejetpack.ui.detail_fav_film

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rifat.moviejetpack.data.repository.MovieRepository
import com.rifat.moviejetpack.data.source.locale.entities.FavEntity
import com.rifat.moviejetpack.data.source.remote.responses.DetailMovieResponse

class DetailFavFilmViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    fun addToFav(movieResponse: DetailMovieResponse): LiveData<Map<String, Any>> =
        movieRepository.addFav(movieResponse)

    fun deleteById(id: String): LiveData<Map<String, Any>> = movieRepository.deleteFavById(id)

    fun checkIsFav(id: String): LiveData<FavEntity> = movieRepository.getFavById(id)
}