package com.rifat.moviejetpack.utils.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rifat.moviejetpack.data.source.remote.responses.SeriesResponse
import com.rifat.moviejetpack.databinding.ItemMovieBinding
import com.rifat.moviejetpack.ui.detail_series.DetailSeriesActivity
import java.util.ArrayList

class ListSeriesAdapter : RecyclerView.Adapter<ListSeriesAdapter.ViewHolder>() {

    private val data = ArrayList<SeriesResponse>()

    fun setData(series: List<SeriesResponse>?) {
        if (series == null) return
        this.data.clear()
        this.data.addAll(series)

        this.notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(series: SeriesResponse) {
            with(binding) {
                txtRate.text = series.vote_average.toString()
                txtTitle.text = series.name
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500" + series.poster_path)
                    .into(imageview)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailSeriesActivity::class.java)
                    intent.putExtra(DetailSeriesActivity.EXTRA_ID, series.id)
                    intent.putExtra(DetailSeriesActivity.EXTRA_FROMFAV, false)
                    itemView.context.startActivity(intent)
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemMovieBinding =
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemMovieBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val series = data[position]
        holder.bind(series)
    }

    override fun getItemCount(): Int = data.size
}