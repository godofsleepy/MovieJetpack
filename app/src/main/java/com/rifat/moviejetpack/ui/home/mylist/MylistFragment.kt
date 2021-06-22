package com.rifat.moviejetpack.ui.home.mylist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rifat.moviejetpack.R
import com.rifat.moviejetpack.databinding.FragmentMylistBinding
import com.rifat.moviejetpack.utils.adapter.ListFavAdapter
import com.rifat.moviejetpack.utils.viewmodel.ViewModelFactory

class MylistFragment : Fragment() {

    private var _binding: FragmentMylistBinding? = null
    private val binding get() = _binding as FragmentMylistBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMylistBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null){
            val factory = ViewModelFactory.getInstance(requireContext())
            val viewModel = ViewModelProvider(this, factory)[MylistViewModel::class.java]
            val listFavAdapter = ListFavAdapter(requireContext())

            viewModel.getFav().observe(this, { data ->
            if (data.isNotEmpty())
                binding.txtList.visibility = View.INVISIBLE
                listFavAdapter.setData(data)
                listFavAdapter.notifyDataSetChanged()
            })

            with(binding.listSeason){
                binding.listSeason.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                binding.listSeason.setHasFixedSize(false)
                binding.listSeason.adapter = listFavAdapter
            }
        }
    }
}