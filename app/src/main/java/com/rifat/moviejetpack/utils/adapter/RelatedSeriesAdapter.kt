package com.rifat.moviejetpack.utils.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rifat.moviejetpack.data.source.remote.responses.SeriesResponse
import com.rifat.moviejetpack.databinding.ItemRelatedBinding
import com.rifat.moviejetpack.ui.detail_series.DetailSeriesActivity
import java.util.ArrayList

class RelatedSeriesAdapter : RecyclerView.Adapter<RelatedSeriesAdapter.ViewHolder>() {
    private val data = ArrayList<SeriesResponse>()
    private var seriesId = ""

    fun setData(series: List<SeriesResponse>?) {
        if (series == null) return
        this.data.clear()
        this.data.addAll(series)

        this.notifyDataSetChanged()
    }

    fun setAdapterSeries(id: String){
        seriesId = id
    }

    inner class ViewHolder(private val binding: ItemRelatedBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(series: SeriesResponse) {
            with(binding) {
                cardView.visibility = View.VISIBLE
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500"+ series.poster_path)
                    .into(imageview)
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailSeriesActivity::class.java)
                    intent.putExtra(DetailSeriesActivity.EXTRA_ID, series.id)
                    itemView.context.startActivity(intent)
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemRelatedBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val series = data[position]
        if (series.id.toString() != seriesId)
            holder.bind(series)
    }

    override fun getItemCount(): Int = data.size
}