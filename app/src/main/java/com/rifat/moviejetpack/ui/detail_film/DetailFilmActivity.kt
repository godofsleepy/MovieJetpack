package com.rifat.moviejetpack.ui.detail_film

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.rifat.moviejetpack.databinding.ActivityDetailFilmBinding

import com.rifat.moviejetpack.utils.adapter.RelatedMovieAdapter
import com.rifat.moviejetpack.utils.viewmodel.ViewModelFactory
import java.util.*


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
        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailFilmViewModel::class.java]
        val relatedMovieAdapter = RelatedMovieAdapter()
        val extras = intent.extras
        if (extras != null) {
            val movieId: Int = extras.getInt(EXTRA_MOVIES)
            viewModel.getDetailFilm(movieId).observe(this, { movie ->
                binding.progressBar.visibility = View.GONE
                binding.toolbarLayout.title = "\"${movie.tagline}\""
                binding.txtTitle.text = movie.title
                binding.txtDate.text = movie.release_date
                binding.txtDesc.text = movie.overview
                binding.txtRate.text = movie.vote_average.toString()
                binding.txtLanguage.text = movie.original_language.uppercase(Locale.getDefault())
                if (movie.productionCompany.isNotEmpty()) {
                    Glide.with(applicationContext)
                        .load("https://image.tmdb.org/t/p/w500" + movie.productionCompany[0].logo)
                        .into(binding.profileImage)
                }
                if (movie.genres.isNotEmpty()) {
                    if (movie.genres.size == 1) {
                        binding.txtGenres.text = movie.genres[0].name
                    } else {
                        val genres = "${movie.genres[0].name}, ${movie.genres[1].name}"
                        binding.txtGenres.text = genres
                    }
                    viewModel.getRelatedMovie(movie.genres[0].id.toString()).observe(this, {
                        binding.progress.visibility = View.GONE
                        relatedMovieAdapter.setAdapterMovie(movie.id.toString())
                        relatedMovieAdapter.setData(it)
                        relatedMovieAdapter.notifyDataSetChanged()
                    })
                } else {
                    binding.textView2.visibility = View.GONE
                }
                binding.textView2.isGone = !movie.adult
                Glide.with(applicationContext)
                    .load("https://image.tmdb.org/t/p/w500" + movie.backdrop_path)
                    .into(binding.detailImage)
                Glide.with(applicationContext)
                    .load("https://image.tmdb.org/t/p/w500" + movie.poster_path)
                    .into(binding.imgPoster)
                if (movie.homepage.isNotEmpty()) {
                    binding.button.setOnClickListener {
                        val uriUrl: Uri = Uri.parse(movie.homepage)
                        val launchBrowser = Intent(Intent.ACTION_VIEW, uriUrl)
                        startActivity(launchBrowser)
                    }
                } else {
                    binding.button.visibility = View.GONE
                }
                binding.buttonAdd.setOnClickListener {
                    viewModel.addToFav(movie)
                }
            })

            with(binding.listRelated) {
                binding.listRelated.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                binding.listRelated.setHasFixedSize(false)
                binding.listRelated.adapter = relatedMovieAdapter
            }

        }
    }
}