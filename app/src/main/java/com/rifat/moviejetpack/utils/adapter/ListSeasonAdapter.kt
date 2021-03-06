package com.rifat.moviejetpack.utils.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rifat.moviejetpack.data.source.remote.responses.SeasonResponse
import com.rifat.moviejetpack.databinding.ItemSeasonBinding
import java.util.*

class ListSeasonAdapter : RecyclerView.Adapter<ListSeasonAdapter.ViewHolder>() {

    private val data = ArrayList<SeasonResponse>()

    fun setData(seasons: List<SeasonResponse>?) {
        if (seasons == null) return
        this.data.clear()
        this.data.addAll(seasons)

        this.notifyDataSetChanged()
    }


    inner class ViewHolder(private val binding: ItemSeasonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(season: SeasonResponse) {
            with(binding) {
                textView4.text = season.name
                textView6.text = "${season.episode} Episode"
                txtDesc.text = season.overview
                textView7.text = season.release
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500" + season.poster_path)
                    .into(seasonPoster)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemSeasonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val season = data[position]
        holder.bind(season)
    }

    override fun getItemCount(): Int = data.size
}
