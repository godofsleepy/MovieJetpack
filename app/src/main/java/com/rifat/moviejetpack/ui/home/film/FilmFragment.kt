package com.rifat.moviejetpack.ui.home.film

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.rifat.moviejetpack.databinding.FragmentFilmBinding
import com.rifat.moviejetpack.utils.adapter.ListFilmAdapter
import com.rifat.moviejetpack.utils.adapter.ListGenreAdapter
import com.rifat.moviejetpack.utils.viewmodel.ViewModelFactory


class FilmFragment : Fragment() {

    private var _binding: FragmentFilmBinding? = null
    private val binding get() = _binding as FragmentFilmBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFilmBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireContext())
            val viewModel = ViewModelProvider(this, factory)[FilmViewModel::class.java]
            val filmAdapter = ListFilmAdapter()
            val genreAdapter = ListGenreAdapter()

            viewModel.getMovies().observe(this) { movies ->
                binding.progressBar.visibility = View.GONE
                filmAdapter.setData(movies)
                filmAdapter.notifyDataSetChanged()
                binding.listMovie.visibility = View.VISIBLE
            }

            with(binding.listMovie) {
                binding.listMovie.layoutManager =
                    StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                binding.listMovie.setHasFixedSize(false)
                binding.listMovie.adapter = filmAdapter
            }

            viewModel.getGenres().observe(this, {genres ->
                binding.progressBar2.visibility = View.GONE
                genreAdapter.setData(genres)
                genreAdapter.notifyDataSetChanged()
            })

            with(binding.listgenre) {
                binding.listgenre.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                binding.listgenre.setHasFixedSize(false)
                binding.listgenre.adapter = genreAdapter
            }

        }
    }
}