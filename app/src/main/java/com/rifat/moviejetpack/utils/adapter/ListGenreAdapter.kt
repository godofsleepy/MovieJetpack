package com.rifat.moviejetpack.utils.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rifat.moviejetpack.R
import com.rifat.moviejetpack.data.entities.GenreEntity
import kotlinx.android.synthetic.main.item_genre.view.*
import java.util.ArrayList

class ListGenreAdapter : RecyclerView.Adapter<ListGenreAdapter.ViewHolder>() {

    private val data = ArrayList<GenreEntity>()

    fun setData(data: List<GenreEntity>?) {
        if (data == null) return
        this.data.clear()
        this.data.addAll(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_genre, parent, false)
        return ViewHolder(view)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(text: String, position: Int) {
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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val genre = data[position]
        holder.bind(genre.name, position)
    }

    override fun getItemCount(): Int = data.size

}