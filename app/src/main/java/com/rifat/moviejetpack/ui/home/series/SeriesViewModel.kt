package com.rifat.moviejetpack.ui.home.series

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rifat.moviejetpack.data.MovieRepository
import com.rifat.moviejetpack.data.entities.GenreEntity
import com.rifat.moviejetpack.data.entities.MovieEntity
import com.rifat.moviejetpack.data.entities.SeriesEntity
import com.rifat.moviejetpack.data.repository.SeriesRepository

class SeriesViewModel(private val seriesRepository: SeriesRepository) : ViewModel() {
    fun getSeries() : LiveData<List<SeriesEntity>> = seriesRepository.getListSeries()
    fun getGenres() : LiveData<List<GenreEntity>> = seriesRepository.getSeriesGenre()
}