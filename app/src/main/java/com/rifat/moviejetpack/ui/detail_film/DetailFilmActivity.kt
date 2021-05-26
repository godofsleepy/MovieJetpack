package com.rifat.moviejetpack.ui.detail_film

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isGone
import com.bumptech.glide.Glide
import com.rifat.moviejetpack.data.entities.MovieEntity
import com.rifat.moviejetpack.databinding.ActivityDetailFilmBinding


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


        val extras = intent.extras
        if (extras != null) {
            val movie: MovieEntity = extras.getSerializable(EXTRA_MOVIES) as MovieEntity
            binding.txtTitle.text = movie.title
            binding.txtDate.text = movie.release_date
            binding.txtDesc.text = movie.overview
            binding.txtRate.text = movie.vote_average.toString()
            binding.cardAdult.isGone = !movie.adult
            Glide.with(applicationContext)
                .load("https://image.tmdb.org/t/p/w500"+ movie.poster_path)
                .into(binding.detailImage)
        }
    }
}