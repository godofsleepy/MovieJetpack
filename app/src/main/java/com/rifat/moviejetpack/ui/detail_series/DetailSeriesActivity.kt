package com.rifat.moviejetpack.ui.detail_series

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.rifat.moviejetpack.databinding.ActivityDetailSeriesBinding
import com.rifat.moviejetpack.utils.viewmodel.ViewModelFactory

class DetailSeriesActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_SERIES = "extra_series"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailSeriesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[DetailSeriesViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val seriesId: Int = extras.getInt(EXTRA_SERIES)
            viewModel.getDetailSeries(seriesId).observe(this, { series ->
                binding.progressBar.visibility = View.GONE
                binding.txtTitle.text = series.name
                binding.txtDate.text = series.first_air_date
                binding.toolbarLayout.title = "\"${series.tagline}\""
                binding.txtDesc.text = series.overview
                binding.txtLanguage.text = series.original_language.uppercase()
                binding.txtRate.text = series.vote_average.toString()
                binding.textView2.isGone = !series.adult
                binding.txtTotalepisode.text = "${series.episode} Episode"
                binding.txtTotalseason.text = "${series.season} Season"
                if (series.productionCompany.isNotEmpty()) {
                    Glide.with(applicationContext)
                        .load("https://image.tmdb.org/t/p/w500" + series.productionCompany[0].logo)
                        .into(binding.profileImage)
                }
                if (series.genres.isNotEmpty()) {
                    if (series.genres.size == 1) {
                        binding.txtGenres.text = series.genres[0].name
                    } else {
                        val genres = "${series.genres[0].name}, ${series.genres[1].name}"
                        binding.txtGenres.text = genres
                    }
                } else {
                    binding.textView2.visibility = View.GONE
                }
                if (series.networksList.isNotEmpty()){
                    Glide.with(applicationContext)
                        .load("https://image.tmdb.org/t/p/w500" + series.networksList[0].logo)
                        .into(binding.networkImage)
                }else {
                    binding.networkImage.visibility = View.GONE
                }
                Glide.with(applicationContext)
                    .load("https://image.tmdb.org/t/p/w500" + series.backdrop_path)
                    .into(binding.detailImage)
                Glide.with(applicationContext)
                    .load("https://image.tmdb.org/t/p/w500" + series.poster_path)
                    .into(binding.imgPoster)
                if (series.homepage.isNotEmpty()) {
                    binding.button.setOnClickListener {
                        val uriUrl: Uri = Uri.parse(series.homepage)
                        val launchBrowser = Intent(Intent.ACTION_VIEW, uriUrl)
                        startActivity(launchBrowser)
                    }
                } else {
                    binding.button.visibility = View.GONE
                }
            })

        }
    }
}