package com.extcode.project.movieappjetpacksubmission2.ui.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.extcode.project.movieappjetpacksubmission2.data.MovieAppRepository
import com.extcode.project.movieappjetpacksubmission2.data.source.local.entity.TvShowEntity

class TvShowsViewModel(private val movieAppRepository: MovieAppRepository) : ViewModel() {

    fun getTvShows(): LiveData<List<TvShowEntity>> = movieAppRepository.getAllTvShows()

}