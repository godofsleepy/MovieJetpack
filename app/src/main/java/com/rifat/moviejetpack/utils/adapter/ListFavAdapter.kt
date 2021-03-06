package com.rifat.moviejetpack.utils.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rifat.moviejetpack.R
import com.rifat.moviejetpack.data.source.locale.entities.FavEntity
import com.rifat.moviejetpack.databinding.ItemSeasonBinding
import com.rifat.moviejetpack.ui.detail_film.DetailFilmActivity
import com.rifat.moviejetpack.ui.detail_series.DetailSeriesActivity

class ListFavAdapter : PagedListAdapter<FavEntity, ListFavAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<FavEntity> =
            object : DiffUtil.ItemCallback<FavEntity>() {
                override fun areItemsTheSame(oldItem: FavEntity, newItem: FavEntity): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: FavEntity, newItem: FavEntity): Boolean {
                    return oldItem == newItem
                }

            }
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
                txtDesc.maxLines = 12
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
                        Log.d("EYY", "in here ${id[1].toInt()}")
                        val intent = Intent(itemView.context, DetailSeriesActivity::class.java)
                        intent.putExtra(DetailSeriesActivity.EXTRA_ID, id[1].toInt())
                        intent.putExtra(DetailSeriesActivity.EXTRA_FROMFAV, true)
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
        holder.bind(getItem(position) as FavEntity)
    }

}
