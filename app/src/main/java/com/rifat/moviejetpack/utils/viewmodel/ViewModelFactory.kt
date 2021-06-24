package com.rifat.moviejetpack.utils.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rifat.moviejetpack.data.repository.MovieRepository
import com.rifat.moviejetpack.data.repository.SeriesRepository
import com.rifat.moviejetpack.di.Injection
import com.rifat.moviejetpack.ui.detail_fav_film.DetailFavFilmViewModel
import com.rifat.moviejetpack.ui.detail_film.DetailFilmViewModel
import com.rifat.moviejetpack.ui.detail_series.DetailSeriesViewModel
import com.rifat.moviejetpack.ui.home.film.FilmViewModel
import com.rifat.moviejetpack.ui.home.mylist.MylistViewModel
import com.rifat.moviejetpack.ui.home.series.SeriesViewModel


class ViewModelFactory private constructor(
    private val mMovierepository: MovieRepository,
    private val mSeriesRepository: SeriesRepository
) : ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(
                    Injection.provideMovieRepository(context),
                    Injection.provideSeriesRepository(context)
                )
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(FilmViewModel::class.java) -> {
                FilmViewModel(mMovierepository) as T
            }
            modelClass.isAssignableFrom(DetailFilmViewModel::class.java) -> {
                DetailFilmViewModel(mMovierepository) as T
            }
            modelClass.isAssignableFrom(SeriesViewModel::class.java) -> {
                SeriesViewModel(mSeriesRepository) as T
            }
            modelClass.isAssignableFrom(DetailSeriesViewModel::class.java) -> {
                DetailSeriesViewModel(mSeriesRepository) as T
            }
            modelClass.isAssignableFrom(MylistViewModel::class.java) -> {
                MylistViewModel(mMovierepository) as T
            }
            modelClass.isAssignableFrom(DetailFavFilmViewModel::class.java) -> {
                DetailFavFilmViewModel(mMovierepository) as T
            }
            modelClass.isAssignableFrom(DetailSeriesViewModel::class.java) -> {
                DetailSeriesViewModel(mSeriesRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}