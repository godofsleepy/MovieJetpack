package com.rifat.moviejetpack.ui.detail_series

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isGone
import com.bumptech.glide.Glide
import com.rifat.moviejetpack.data.entities.SeriesEntity
import com.rifat.moviejetpack.databinding.ActivityDetailSeriesBinding

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

        val extras = intent.extras
        if (extras != null) {
            val series: SeriesEntity = extras.getSerializable(EXTRA_SERIES) as SeriesEntity
            binding.txtTitle.text = series.name
            binding.txtDate.text = series.first_air_date
            binding.txtDesc.text = series.overview
            binding.txtRate.text = series.vote_average.toString()
            binding.cardAdult.isGone = !series.adult
            Glide.with(applicationContext)
                .load("https://image.tmdb.org/t/p/w500"+ series.backdrop_path)
                .into(binding.detailImage)
        }
    }
}