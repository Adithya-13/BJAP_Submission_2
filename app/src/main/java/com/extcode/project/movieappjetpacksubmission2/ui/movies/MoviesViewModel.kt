package com.extcode.project.movieappjetpacksubmission2.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.extcode.project.movieappjetpacksubmission2.data.MovieAppRepository
import com.extcode.project.movieappjetpacksubmission2.data.source.local.entity.MovieEntity

class MoviesViewModel(private val movieAppRepository: MovieAppRepository) : ViewModel() {

    fun getMovies(): LiveData<List<MovieEntity>> = movieAppRepository.getAllMovies()

}