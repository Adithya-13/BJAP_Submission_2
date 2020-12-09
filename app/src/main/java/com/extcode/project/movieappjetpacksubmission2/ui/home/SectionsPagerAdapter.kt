package com.extcode.project.movieappjetpacksubmission2.ui.home

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.extcode.project.movieappjetpacksubmission2.R
import com.extcode.project.movieappjetpacksubmission2.ui.movies.MoviesFragment
import com.extcode.project.movieappjetpacksubmission2.ui.tvshows.TvShowsFragment

class SectionsPagerAdapter(private val mContext: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    @StringRes
    private val tabTitles = intArrayOf(
        R.string.movies,
        R.string.tvshows
    )

    private val fragment: List<Fragment> = listOf(
        MoviesFragment(),
        TvShowsFragment()
    )

    override fun getPageTitle(position: Int): CharSequence {
        return mContext.getString(tabTitles[position])
    }

    override fun getCount() = tabTitles.size

    override fun getItem(position: Int): Fragment {
        return fragment[position]
    }
}