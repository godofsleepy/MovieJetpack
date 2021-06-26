package com.rifat.moviejetpack.utils.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rifat.moviejetpack.R
import com.rifat.moviejetpack.data.source.locale.entities.FavEntity
import com.rifat.moviejetpack.databinding.ItemSeasonBinding
import com.rifat.moviejetpack.ui.detail_film.DetailFilmActivity
import com.rifat.moviejetpack.ui.detail_series.DetailSeriesActivity
import java.util.ArrayList

class ListFavAdapter(val context: Context) : RecyclerView.Adapter<ListFavAdapter.ViewHolder>() {

    private val data = ArrayList<FavEntity>()

    fun setData(fav: List<FavEntity>?) {
        if (fav == null) return
        this.data.clear()
        this.data.addAll(fav)

        this.notifyDataSetChanged()
    }


    inner class ViewHolder(private val binding: ItemSeasonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(favEntity: FavEntity) {
            with(binding) {
                val id = favEntity.id.split("-").toTypedArray()
                textView4.text = favEntity.title
                textView6.text = favEntity.vote_average.toString()
                textView6.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_baseline_star_24,
                    0,
                    0,
                    0
                )
                txtDesc.text = favEntity.overview
                if (id[0] == "m") {
                    textView7.text = "Movie"
                } else {
                    textView7.text = "Series"
                }
                textView7.setTextColor(Color.parseColor("#be122b"))


                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500" + favEntity.poster)
                    .into(seasonPoster)
                itemView.setOnClickListener {


                    if (id[0] == "m") {
                        val intent = Intent(itemView.context, DetailFilmActivity::class.java)
                        intent.putExtra(DetailFilmActivity.EXTRA_ID, id[1].toInt())
                        intent.putExtra(DetailFilmActivity.EXTRA_FROMFAV, true)
                        itemView.context.startActivity(intent)
                    } else {
                        val intent = Intent(itemView.context, DetailFilmActivity::class.java)
                        intent.putExtra(DetailSeriesActivity.EXTRA_SERIES, id[1].toInt())
                        itemView.context.startActivity(intent)

                    }
                }
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
        val fav = data[position]
        holder.bind(fav)
    }

    override fun getItemCount(): Int = data.size
}
