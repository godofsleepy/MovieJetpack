package com.rifat.moviejetpack.ui.detail_film

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isGone
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.rifat.moviejetpack.data.entities.MovieEntity
import com.rifat.moviejetpack.databinding.ActivityDetailFilmBinding
import com.rifat.moviejetpack.utils.getJsonDataFromAsset


class DetailFilmActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIES = "extra_movies"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailFilmBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[DetailFilmViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val movieId: Int = extras.getInt(EXTRA_MOVIES)
            val movie : MovieEntity = viewModel.getDetailFilm(movieId, getJsonDataFromAsset(applicationContext.applicationContext, "movies.json"))
            binding.txtTitle.text = movie.title
            binding.txtDate.text = movie.release_date
            binding.txtDesc.text = movie.overview
            binding.txtRate.text = movie.vote_average.toString()
            binding.cardAdult.isGone = !movie.adult
            Glide.with(applicationContext)
                .load("https://image.tmdb.org/t/p/w500" + movie.backdrop_path)
                .into(binding.detailImage)
        }
    }
}