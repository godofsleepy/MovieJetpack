package com.rifat.moviejetpack.ui.detail_series

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rifat.moviejetpack.data.entities.DetailMovieEntity
import com.rifat.moviejetpack.data.entities.DetailSeriesEntity
import com.rifat.moviejetpack.data.entities.SeriesEntity
import com.rifat.moviejetpack.data.repository.SeriesRepository

class DetailSeriesViewModel(private val seriesRepository: SeriesRepository) : ViewModel() {

    fun getDetailSeries(id: Int): LiveData<DetailSeriesEntity> = seriesRepository.getDetailSeries(id.toString())
}