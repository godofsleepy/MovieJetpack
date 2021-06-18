package com.rifat.moviejetpack.ui.home.film

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.rifat.moviejetpack.R
import com.rifat.moviejetpack.utils.adapter.ListFilmAdapter
import com.rifat.moviejetpack.utils.adapter.ListGenreAdapter
import com.rifat.moviejetpack.utils.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_film.*


class FilmFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_film, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance()
            val viewModel = ViewModelProvider(this, factory)[FilmViewModel::class.java]
            val filmAdapter = ListFilmAdapter()
            val genreAdapter = ListGenreAdapter()

            viewModel.getMovies().observe(this, { movies ->
                progressBar.visibility = View.GONE
                filmAdapter.setData(movies)
                filmAdapter.notifyDataSetChanged()
                list_movie.visibility = View.VISIBLE
            })

            with(list_movie) {
                list_movie.layoutManager =
                    StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                list_movie.setHasFixedSize(false)
                list_movie.adapter = filmAdapter
            }

            viewModel.getGenres().observe(this, {genres ->
                progressBar2.visibility = View.GONE
                genreAdapter.setData(genres)
                genreAdapter.notifyDataSetChanged()
            })

            with(listgenre) {
                listgenre.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                listgenre.setHasFixedSize(false)
                listgenre.adapter = genreAdapter
            }

        }
    }
}