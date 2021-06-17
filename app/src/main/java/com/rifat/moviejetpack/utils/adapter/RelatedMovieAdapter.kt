package com.rifat.moviejetpack.utils.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rifat.moviejetpack.data.entities.MovieEntity
import com.rifat.moviejetpack.databinding.ItemRelatedBinding
import com.rifat.moviejetpack.ui.detail_film.DetailFilmActivity
import java.util.ArrayList

class RelatedMovieAdapter : RecyclerView.Adapter<RelatedMovieAdapter.ViewHolder>() {
    private val data = ArrayList<MovieEntity>()
    private var movieId = ""

    fun setData(movies: List<MovieEntity>?) {
        if (movies == null) return
        this.data.clear()
        this.data.addAll(movies)

        this.notifyDataSetChanged()
    }

    fun setAdapterMovie(id: String){
        movieId = id
    }
    
    inner class ViewHolder(private val binding: ItemRelatedBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            with(binding) {
                cardView.visibility = View.VISIBLE
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
        return ViewHolder(ItemRelatedBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = data[position]
        if (movie.id.toString() != movieId)
            holder.bind(movie)
    }

    override fun getItemCount(): Int = data.size

}