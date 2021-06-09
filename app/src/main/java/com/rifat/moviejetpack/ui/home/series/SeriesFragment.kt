package com.rifat.moviejetpack.ui.home.series

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.rifat.moviejetpack.R
import com.rifat.moviejetpack.utils.adapter.ListGenreAdapter
import com.rifat.moviejetpack.utils.adapter.ListSeriesAdapter
import com.rifat.moviejetpack.utils.getJsonDataFromAsset
import kotlinx.android.synthetic.main.fragment_series.*

class SeriesFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_series, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[SeriesViewModel::class.java]
            val series = viewModel.getSeries(getJsonDataFromAsset(activity!!.applicationContext, "series.json"))

            val seriesAdapter = ListSeriesAdapter()
            seriesAdapter.setData(series)

            list_series.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            list_series.setHasFixedSize(false)
            list_series.adapter = seriesAdapter


//            val genreAdapter = ListGenreAdapter()
//            genreAdapter.setData(listOf("All", "Horror", "Drama", "Adventure", "Romance"))
//
//            listgenre.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//            listgenre.setHasFixedSize(false)
//            listgenre.adapter = genreAdapter
        }
    }
}