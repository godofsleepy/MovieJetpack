package com.rifat.moviejetpack.utils.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rifat.moviejetpack.R
import com.rifat.moviejetpack.data.source.locale.entities.FavEntity
import com.rifat.moviejetpack.databinding.ItemSeasonBinding
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
                textView4.text = favEntity.title
                textView6.text = favEntity.vote_average.toString()
                textView6.setCompoundDrawables(ContextCompat.getDrawable(context, R.drawable.ic_baseline_star_24), null, null, null)
                txtDesc.text = favEntity.overview
                textView7.text = favEntity.release_date
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500" + favEntity.poster)
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
        val fav = data[position]
        holder.bind(fav)
    }

    override fun getItemCount(): Int = data.size
}
