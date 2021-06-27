package com.rifat.moviejetpack.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rifat.moviejetpack.databinding.ActivityHomeBinding
import com.rifat.moviejetpack.utils.adapter.HomePagerAdapter

class HomeActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = HomePagerAdapter(this, supportFragmentManager)
        binding.viewPager.adapter = sectionsPagerAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)
        supportActionBar?.elevation = 0f
    }
}