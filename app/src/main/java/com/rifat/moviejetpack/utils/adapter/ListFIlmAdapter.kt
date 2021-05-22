package com.rifat.moviejetpack.utils.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rifat.moviejetpack.data.entities.MovieEntity
import com.rifat.moviejetpack.databinding.ItemMovieBinding
import kotlinx.android.synthetic.main.item_genre.view.*
import kotlinx.android.synthetic.main.item_movie.view.*
import java.util.ArrayList

class ListFIlmAdapter : RecyclerView.Adapter<ListFIlmAdapter.ViewHolder>() {
    private val data = ArrayList<MovieEntity>()

    fun setData(movies: List<MovieEntity>?) {
        if (movies == null) return
        this.data.clear()
        this.data.addAll(movies)

        this.notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            with(binding) {
                txtRate.text = movie.vote_average.toString()
                txtTitle.text = movie.title
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500"+ movie.poster_path)
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
        val movie = data[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = data.size
}