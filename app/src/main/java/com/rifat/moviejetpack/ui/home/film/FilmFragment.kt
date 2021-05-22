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
import com.rifat.moviejetpack.utils.adapter.ListFIlmAdapter
import com.rifat.moviejetpack.utils.adapter.ListGenreAdapter
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
            val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[FilmViewModel::class.java]
            val movies = viewModel.getMovies(context)

            val filmAdapter = ListFIlmAdapter()
            filmAdapter.setData(movies)

            list_movie.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            list_movie.setHasFixedSize(false)
            list_movie.adapter = filmAdapter


            val genreAdapter = ListGenreAdapter()
            genreAdapter.setData(listOf("All", "Horror", "Drama", "Adventure", "Romance"))

            listgenre.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            listgenre.setHasFixedSize(false)
            listgenre.adapter = genreAdapter
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }
}