package com.rifat.moviejetpack.ui.home.series

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rifat.moviejetpack.data.repository.SeriesRepository
import com.rifat.moviejetpack.data.source.remote.responses.GenreResponse
import com.rifat.moviejetpack.data.source.remote.responses.SeriesResponse

class SeriesViewModel(private val seriesRepository: SeriesRepository) : ViewModel() {
    fun getSeries() : LiveData<List<SeriesResponse>> = seriesRepository.getListSeries()
    fun getGenres() : LiveData<List<GenreResponse>> = seriesRepository.getSeriesGenre()
}