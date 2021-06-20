package com.rifat.moviejetpack.utils.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rifat.moviejetpack.data.source.remote.responses.GenreResponse
import com.rifat.moviejetpack.databinding.ItemGenreBinding
import java.util.ArrayList

class ListGenreAdapter : RecyclerView.Adapter<ListGenreAdapter.ViewHolder>() {

    private val data = ArrayList<GenreResponse>()

    fun setData(data: List<GenreResponse>?) {
        if (data == null) return
        this.data.clear()
        this.data.addAll(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemGenreBinding =
            ItemGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemGenreBinding)
    }

    inner class ViewHolder(private val binding: ItemGenreBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(text: String, position: Int) {
            with(binding){
                if (position == 0) {
                    with(itemView) {
                        genrebutton.text = text
                        genrebutton.setBackgroundColor(Color.parseColor("#be122b"))
                    }
                } else {
                    with(itemView) {
                        genrebutton.text = text
                    }
                }
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val genre = data[position]
        holder.bind(genre.name, position)
    }

    override fun getItemCount(): Int = data.size

}