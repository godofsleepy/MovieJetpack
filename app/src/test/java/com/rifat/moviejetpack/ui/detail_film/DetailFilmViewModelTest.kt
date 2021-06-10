package com.rifat.moviejetpack.ui.detail_film

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nhaarman.mockitokotlin2.verify
import com.rifat.moviejetpack.data.MovieRepository
import com.rifat.moviejetpack.data.entities.DetailMovieEntity
import com.rifat.moviejetpack.data.entities.MovieEntity
import org.junit.Assert
import org.junit.Test

import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailFilmViewModelTest {

    private lateinit var viewModel: DetailFilmViewModel
    private lateinit var data : String

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<DetailMovieEntity>

    @Before
    fun setUp() {
        viewModel = DetailFilmViewModel(movieRepository)
        data = "{\"adult\":false,\"backdrop_path\":\"\\/6MKr3KgOLmzOP6MSuZERO41Lpkt.jpg\",\"belongs_to_collection\":{\"id\":837007,\"name\":\"Cruella Collection\",\"poster_path\":null,\"backdrop_path\":null},\"budget\":200000000,\"genres\":[{\"id\":35,\"name\":\"Comedy\"},{\"id\":80,\"name\":\"Crime\"}],\"homepage\":\"https:\\/\\/movies.disney.com\\/cruella\",\"id\":337404,\"imdb_id\":\"tt3228774\",\"original_language\":\"en\",\"original_title\":\"Cruella\",\"overview\":\"In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella\\u2019s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.\",\"popularity\":6077.166,\"poster_path\":\"\\/rTh4K5uw9HypmpGslcKd4QfHl93.jpg\",\"production_companies\":[{\"id\":2,\"logo_path\":\"\\/wdrCwmRnLFJhEoH8GSfymY85KHT.png\",\"name\":\"Walt Disney Pictures\",\"origin_country\":\"US\"}],\"production_countries\":[{\"iso_3166_1\":\"US\",\"name\":\"United States of America\"}],\"release_date\":\"2021-05-26\",\"revenue\":88197497,\"runtime\":134,\"spoken_languages\":[{\"english_name\":\"English\",\"iso_639_1\":\"en\",\"name\":\"English\"}],\"status\":\"Released\",\"tagline\":\"Hello Cruel World\",\"title\":\"Cruella\",\"video\":false,\"vote_average\":8.7,\"vote_count\":2318}"
    }

    @Test
    fun getDetailFilm() {
        val gson = Gson()
        val typeMovie = object : TypeToken<DetailMovieEntity>() {}.type
        val dummyMovie: DetailMovieEntity = gson.fromJson(data, typeMovie)
        val movie = MutableLiveData<DetailMovieEntity>()
        movie.value = dummyMovie

        Mockito.`when`(movieRepository.getDetailMovie("837007")).thenReturn(movie)
        val list = viewModel.getDetailFilm(837007).value
        verify(movieRepository).getDetailMovie("837007")
        viewModel.getDetailFilm(837007).observeForever(observer)
        Mockito.verify(observer).onChanged(dummyMovie)

        Assert.assertNotNull(list)
    }

}