package com.rifat.moviejetpack.utils.adapter

import android.content.Context
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rifat.moviejetpack.R
import com.rifat.moviejetpack.ui.home.film.FilmFragment
import com.rifat.moviejetpack.ui.home.mylist.MylistFragment
import com.rifat.moviejetpack.ui.home.series.SeriesFragment

class HomePagerAdapter(private val mContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2,
            R.string.tab_text_3
        )
    }

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> FilmFragment()
            1 -> SeriesFragment()
            2 -> MylistFragment()
            else -> Fragment()
        }

    override fun getPageTitle(position: Int): CharSequence? = mContext.resources.getString(TAB_TITLES[position])

    override fun getCount(): Int = 3

}