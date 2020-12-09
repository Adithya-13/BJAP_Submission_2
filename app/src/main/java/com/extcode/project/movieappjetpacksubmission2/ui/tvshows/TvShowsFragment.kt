package com.extcode.project.movieappjetpacksubmission2.ui.tvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.extcode.project.movieappjetpacksubmission2.databinding.FragmentTvShowsBinding
import com.extcode.project.movieappjetpacksubmission2.viewmodel.ViewModelFactory

class TvShowsFragment : Fragment() {

    private var _fragmentTvShowsBinding: FragmentTvShowsBinding? = null
    private val fragmentTvShowsBinding get() = _fragmentTvShowsBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _fragmentTvShowsBinding = FragmentTvShowsBinding.inflate(layoutInflater, container, false)
        return fragmentTvShowsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            val factory = ViewModelFactory.getInstance()
            val viewModel = ViewModelProvider(this, factory)[TvShowsViewModel::class.java]

            val tvShowsAdapter = TvShowsAdapter()

            fragmentTvShowsBinding.progressBar.visibility = View.VISIBLE
            viewModel.getTvShows().observe(this, { movies ->
                fragmentTvShowsBinding.progressBar.visibility = View.GONE
                tvShowsAdapter.setTvShows(movies)
                tvShowsAdapter.notifyDataSetChanged()
            })

            with(fragmentTvShowsBinding.rvTvShows) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowsAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentTvShowsBinding = null
    }

}