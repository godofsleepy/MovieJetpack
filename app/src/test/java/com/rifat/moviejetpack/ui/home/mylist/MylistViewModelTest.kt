package com.rifat.moviejetpack.ui.home.mylist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.rifat.moviejetpack.data.repository.MovieRepository
import com.rifat.moviejetpack.data.source.locale.entities.FavEntity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MylistViewModelTest {
    private lateinit var viewModel: MylistViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<PagedList<FavEntity>>

    @Mock
    private lateinit var pagedList: PagedList<FavEntity>

    @Before
    fun setUp(){
        viewModel = MylistViewModel(movieRepository)
    }

    @Test
    fun getFav() {
        val dummy = pagedList
        `when`(dummy.size).thenReturn(5)
        val movie = MutableLiveData<PagedList<FavEntity>>()
        movie.value = dummy

        `when`(movieRepository.getAllFav()).thenReturn(movie)
        val result = viewModel.getFav().value
        verify(movieRepository).getAllFav()
        assertNotNull(result)
        assertEquals(5, result?.size)

        viewModel.getFav().observeForever(observer)
        verify(observer).onChanged(dummy)
    }
}