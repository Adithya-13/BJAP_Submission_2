package com.extcode.project.movieappjetpacksubmission2.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.extcode.project.movieappjetpacksubmission2.data.MovieAppRepository
import com.extcode.project.movieappjetpacksubmission2.data.source.local.entity.MovieEntity
import com.extcode.project.movieappjetpacksubmission2.data.source.local.entity.TvShowEntity

class DetailViewModel(private val movieAppRepository: MovieAppRepository) : ViewModel() {

    private lateinit var movieId: String
    private lateinit var tvShowId: String

    fun selectedMovieId(movieId: String) {
        this.movieId = movieId
    }

    fun selectedTvShowId(tvShowId: String){
        this.tvShowId = tvShowId
    }

    fun getMovieDetail(): LiveData<MovieEntity> =
        movieAppRepository.getMovieById(movieId)

    fun getTvShowDetail(): LiveData<TvShowEntity> =
        movieAppRepository.getTvShowById(tvShowId)

}