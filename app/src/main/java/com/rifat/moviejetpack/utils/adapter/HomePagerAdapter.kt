package com.rifat.moviejetpack.utils.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rifat.moviejetpack.ui.home.film.FilmFragment
import com.rifat.moviejetpack.ui.home.mylist.MylistFragment
import com.rifat.moviejetpack.ui.home.series.SeriesFragment

class HomePagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FilmFragment()
            1 -> fragment = SeriesFragment()
            2 -> fragment = MylistFragment()
        }
        return fragment as Fragment
    }

}