package com.rifat.moviejetpack.utils.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rifat.moviejetpack.data.MovieRepository
import com.rifat.moviejetpack.data.repository.SeriesRepository
import com.rifat.moviejetpack.di.Injection
import com.rifat.moviejetpack.ui.detail_film.DetailFilmViewModel
import com.rifat.moviejetpack.ui.detail_series.DetailSeriesViewModel
import com.rifat.moviejetpack.ui.home.film.FilmViewModel
import com.rifat.moviejetpack.ui.home.series.SeriesViewModel


class ViewModelFactory private constructor(
    private val mMovierepository: MovieRepository,
    private val mSeriesRepository: SeriesRepository
) : ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(
                    Injection.provideMovieRepository(),
                    Injection.provideSeriesRepository()
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
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}