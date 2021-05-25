package com.rifat.moviejetpack.utils.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rifat.moviejetpack.data.entities.MovieEntity
import com.rifat.moviejetpack.data.entities.SeriesEntity
import com.rifat.moviejetpack.databinding.ItemMovieBinding
import java.util.ArrayList

class ListSeriesAdapter : RecyclerView.Adapter<ListSeriesAdapter.ViewHolder>() {

    private val data = ArrayList<SeriesEntity>()

    fun setData(series: List<SeriesEntity>?) {
        if (series == null) return
        this.data.clear()
        this.data.addAll(series)

        this.notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(series: SeriesEntity) {
            with(binding) {
                txtRate.text = series.vote_average.toString()
                txtTitle.text = series.name
                com.bumptech.glide.Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500" + series.poster_path)
                    .into(imageview)
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