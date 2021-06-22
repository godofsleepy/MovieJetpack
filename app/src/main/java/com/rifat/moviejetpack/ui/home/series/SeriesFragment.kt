package com.rifat.moviejetpack.ui.home.series

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.rifat.moviejetpack.databinding.FragmentSeriesBinding
import com.rifat.moviejetpack.utils.adapter.ListGenreAdapter
import com.rifat.moviejetpack.utils.adapter.ListSeriesAdapter
import com.rifat.moviejetpack.utils.viewmodel.ViewModelFactory

class SeriesFragment : Fragment() {

    private var _binding: FragmentSeriesBinding? = null
    private val binding get() = _binding as FragmentSeriesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSeriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireContext())
            val viewModel = ViewModelProvider(this, factory)[SeriesViewModel::class.java]
            val seriesAdapter = ListSeriesAdapter()
            val genreAdapter = ListGenreAdapter()

            viewModel.getSeries().observe(this, { series ->
                binding.progressBar3.visibility = View.GONE
                seriesAdapter.setData(series)
                seriesAdapter.notifyDataSetChanged()
                binding.listSeries.visibility = View.VISIBLE
            })
            with(binding.listSeries) {
                binding.listSeries.layoutManager =
                    StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                binding.listSeries.setHasFixedSize(false)
                binding.listSeries.adapter = seriesAdapter
            }

            viewModel.getGenres().observe(this, { genres ->
                binding.progressBar4.visibility = View.GONE
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