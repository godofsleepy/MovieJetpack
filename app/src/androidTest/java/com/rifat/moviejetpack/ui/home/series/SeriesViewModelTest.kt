package com.rifat.moviejetpack.ui.home.series

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.rifat.moviejetpack.utils.getJsonDataFromAsset
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class SeriesViewModelTest {
    private lateinit var viewModel: SeriesViewModel
    private lateinit var data: String
    private lateinit var context: Context

    @Before
    fun setUp() {
        viewModel = SeriesViewModel()
        context = InstrumentationRegistry.getInstrumentation().targetContext
        data = getJsonDataFromAsset(context, "series.json").orEmpty()
    }

    @Test
    fun getAsset() {
        Assert.assertNotNull(data)
        Assert.assertNotEquals("", data)
    }

    @Test
    fun getSeries() {
        val list = viewModel.getSeries(data)
        Assert.assertNotNull(list)
        Assert.assertEquals(20, list.size)
    }
}