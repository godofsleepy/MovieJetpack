package com.rifat.moviejetpack.ui.detail_series

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rifat.moviejetpack.data.repository.SeriesRepository
import com.rifat.moviejetpack.data.source.locale.entities.FavEntity
import com.rifat.moviejetpack.data.source.remote.responses.DetailSeriesResponse
import com.rifat.moviejetpack.data.source.remote.responses.SeriesResponse

class DetailSeriesViewModel(private val seriesRepository: SeriesRepository) : ViewModel() {

    fun getDetailSeries(id: Int): LiveData<DetailSeriesResponse> = seriesRepository.getDetailSeries(id.toString())

    fun getRelatedSeries(idGenre: String) : LiveData<List<SeriesResponse>> = seriesRepository.getListSeriesByGenre(idGenre)

    fun addToFav(detailseriesResponse: DetailSeriesResponse) = seriesRepository.addFav(detailseriesResponse)

    fun deleteById(id: String): LiveData<Map<String, Any>> = seriesRepository.deleteFavById(id)

    fun checkIsFav(id: String): LiveData<FavEntity> = seriesRepository.getFavById(id)
}