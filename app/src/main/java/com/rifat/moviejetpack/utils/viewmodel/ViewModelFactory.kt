package com.rifat.moviejetpack.utils.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rifat.moviejetpack.data.MovieRepository
import com.rifat.moviejetpack.di.Injection
import com.rifat.moviejetpack.ui.home.film.FilmViewModel


class ViewModelFactory private constructor(private val mMovierepository: MovieRepository) : ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideMovieRepository())
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(FilmViewModel::class.java) -> {
                FilmViewModel(mMovierepository) as T
            }
//            modelClass.isAssignableFrom(DetailCourseViewModel::class.java) -> {
//                DetailCourseViewModel(mAcademyRepository) as T
//            }
//            modelClass.isAssignableFrom(BookmarkViewModel::class.java) -> {
//                BookmarkViewModel(mAcademyRepository) as T
//            }
//            modelClass.isAssignableFrom(CourseReaderViewModel::class.java) -> {
//                CourseReaderViewModel(mAcademyRepository) as T
//            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}