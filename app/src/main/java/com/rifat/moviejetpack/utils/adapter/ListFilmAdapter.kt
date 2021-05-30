package com.rifat.moviejetpack.utils.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rifat.moviejetpack.data.entities.MovieEntity
import com.rifat.moviejetpack.databinding.ItemMovieBinding
import com.rifat.moviejetpack.ui.detail_film.DetailFilmActivity
import java.util.ArrayList

class ListFilmAdapter : RecyclerView.Adapter<ListFilmAdapter.ViewHolder>() {
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
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailFilmActivity::class.java)
                    intent.putExtra(DetailFilmActivity.EXTRA_MOVIES, movie.id)
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
        val movie = data[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = data.size
}