package com.rifat.moviejetpack.ui.home.mylist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rifat.moviejetpack.data.repository.MovieRepository
import com.rifat.moviejetpack.data.source.locale.entities.FavEntity

class MylistViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    fun getFav(): LiveData<List<FavEntity>> = movieRepository.getAllFav()
}