package com.rifat.moviejetpack.ui.detail_series

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rifat.moviejetpack.data.entities.DetailSeriesEntity
import com.rifat.moviejetpack.data.entities.SeriesEntity
import com.rifat.moviejetpack.data.repository.SeriesRepository

class DetailSeriesViewModel(private val seriesRepository: SeriesRepository) : ViewModel() {

    fun getDetailSeries(id: Int): LiveData<DetailSeriesEntity> = seriesRepository.getDetailSeries(id.toString())

    fun getRelatedSeries(idGenre: String) : LiveData<List<SeriesEntity>> = seriesRepository.getListSeriesByGenre(idGenre)
}