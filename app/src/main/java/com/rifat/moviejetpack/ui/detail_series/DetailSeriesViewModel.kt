package com.rifat.moviejetpack.ui.detail_series

import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rifat.moviejetpack.data.entities.SeriesEntity

class DetailSeriesViewModel: ViewModel() {

    fun getDetailSeries(id: Int, data: String?): SeriesEntity {
        val emptySeries = SeriesEntity(0, "", "", "", "", "", "", 0.0, false)
        if (data.isNullOrBlank()) return emptySeries
        val gson = Gson()
        val listType = object : TypeToken<List<SeriesEntity>>() {}.type

        val listseries: List<SeriesEntity> = gson.fromJson(data, listType)
        return try {
            val series = listseries.first { it.id == id }
            series
        } catch (e: NoSuchElementException) {
            emptySeries
        }
    }
}