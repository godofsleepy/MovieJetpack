package com.rifat.moviejetpack.ui.home.film

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.rifat.moviejetpack.utils.getJsonDataFromAsset
import org.junit.Assert

import org.junit.Before
import org.junit.Test

import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FilmViewModelTest {
    private lateinit var viewModel: FilmViewModel
    private lateinit var data : String
    private lateinit var context: Context

    @Before
    fun setUp() {
        viewModel = FilmViewModel()
        context = InstrumentationRegistry.getInstrumentation().targetContext
        data = getJsonDataFromAsset(context, "movies.json").orEmpty()
    }

    @Test
    fun getAsset() {
        Assert.assertNotNull(data)
        Assert.assertNotEquals("", data)
    }

    @Test
    fun getMovies() {
        val list = viewModel.getMovies(data)
        Assert.assertNotNull(list)
        Assert.assertEquals(20, list.size)
    }
}