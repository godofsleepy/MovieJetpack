package com.rifat.moviejetpack.ui.detail_series

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.AnimatedVectorDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isGone
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.rifat.moviejetpack.R
import com.rifat.moviejetpack.data.source.remote.responses.DetailSeriesResponse
import com.rifat.moviejetpack.databinding.ActivityDetailSeriesBinding
import com.rifat.moviejetpack.utils.adapter.ListSeasonAdapter
import com.rifat.moviejetpack.utils.adapter.RelatedSeriesAdapter
import com.rifat.moviejetpack.utils.viewmodel.ViewModelFactory

class DetailSeriesActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_FROMFAV = "extra_from_fav"
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailSeriesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailSeriesViewModel::class.java]
        val listSeasonAdapter = ListSeasonAdapter()
        val relatedSeriesAdapter = RelatedSeriesAdapter()
        val extras = intent.extras
        if (extras != null) {
            val seriesId: Int = extras.getInt(EXTRA_ID)
            Log.d("tag", "id : $seriesId")
            val fromFav: Boolean = extras.getBoolean(EXTRA_FROMFAV)
            viewModel.getDetailSeries(seriesId).observe(this, { series ->
                var isFav = false

                viewModel.checkIsFav("s-${series.id}").observe(this, {
                    if (it != null) {
                        isFav = true
                        add(binding)
                    }
                })
                bindView(binding, series)

                if (series.seasons.isNotEmpty()) {
                    listSeasonAdapter.setData(series.seasons)
                    listSeasonAdapter.notifyDataSetChanged()
                }

                if (series.genres.isNotEmpty()) {
                    if (series.genres.size == 1) {
                        binding.txtGenres.text = series.genres[0].name
                    } else {
                        val genres = "${series.genres[0].name}, ${series.genres[1].name}"
                        binding.txtGenres.text = genres
                    }
                    if (!fromFav){
                        viewModel.getRelatedSeries(series.genres[0].id.toString()).observe(this, {
                            binding.progress.visibility = View.GONE
                            relatedSeriesAdapter.setAdapterSeries(series.id.toString())
                            relatedSeriesAdapter.setData(it)
                            relatedSeriesAdapter.notifyDataSetChanged()
                        })
                    }
                    else {
                        binding.textview8.visibility = View.GONE
                        binding.progress.visibility = View.GONE
                    }
                } else {
                    binding.textview8.visibility = View.GONE
                }


                if (series.homepage.isNotEmpty()) {
                    binding.button.setOnClickListener {
                        val uriUrl: Uri = Uri.parse(series.homepage)
                        val launchBrowser = Intent(Intent.ACTION_VIEW, uriUrl)
                        startActivity(launchBrowser)
                    }
                } else {
                    binding.button.visibility = View.GONE
                }
                binding.buttonAdd.setOnClickListener {
                    if (isFav) {
                        viewModel.deleteById("s-${series.id}").observe(this, { map ->
                            if (!(map["status"] as Boolean)) {
                                Toast.makeText(
                                    applicationContext,
                                    map["message"] as String,
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                            close(binding)
                            isFav = false
                        })
                    } else {
                        viewModel.addToFav(series).observe(this, { map ->
                            if (!(map["status"] as Boolean)) {
                                Toast.makeText(
                                    applicationContext,
                                    map["message"] as String,
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                            add(binding)
                            isFav = true
                        })
                    }
                }
            })
            with(binding.rvSeason) {
                binding.rvSeason.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                binding.rvSeason.setHasFixedSize(false)
                binding.rvSeason.adapter = listSeasonAdapter
            }
            with(binding.listRelated) {
                binding.listRelated.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                binding.listRelated.setHasFixedSize(false)
                binding.listRelated.adapter = relatedSeriesAdapter
            }

        }
    }

    @SuppressLint("SetTextI18n")
    private fun bindView(
        binding: ActivityDetailSeriesBinding,
        series: DetailSeriesResponse
    ) {
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

        Glide.with(applicationContext)
            .load("https://image.tmdb.org/t/p/w500" + series.backdrop_path)
            .into(binding.detailImage)

        Glide.with(applicationContext)
            .load("https://image.tmdb.org/t/p/w500" + series.poster_path)
            .into(binding.imgPoster)

        if (series.networksList.isNotEmpty()) {
            Glide.with(applicationContext)
                .load("https://image.tmdb.org/t/p/w500" + series.networksList[0].logo)
                .into(binding.networkImage)
        } else {
            binding.cardView4.visibility = View.GONE
        }

        if (series.productionCompany.isNotEmpty()) {
            Glide.with(applicationContext)
                .load("https://image.tmdb.org/t/p/w500" + series.productionCompany[0].logo)
                .into(binding.profileImage)
        }
    }

    private fun add(binding: ActivityDetailSeriesBinding) {
        binding.buttonAdd.setIconResource(R.drawable.avd_add_to_delete)
        val buttonAnimate =
            binding.buttonAdd.compoundDrawables[0] as AnimatedVectorDrawable
        buttonAnimate.start()
    }

    private fun close(binding: ActivityDetailSeriesBinding) {
        binding.buttonAdd.setIconResource(R.drawable.avd_delete_to_add)
        val buttonAnimate =
            binding.buttonAdd.compoundDrawables[0] as AnimatedVectorDrawable
        buttonAnimate.start()
    }
}