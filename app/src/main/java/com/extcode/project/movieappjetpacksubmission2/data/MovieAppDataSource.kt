package com.extcode.project.movieappjetpacksubmission2.data

import androidx.lifecycle.LiveData
import com.extcode.project.movieappjetpacksubmission2.data.source.local.entity.MovieEntity
import com.extcode.project.movieappjetpacksubmission2.data.source.local.entity.TvShowEntity

interface MovieAppDataSource {

    fun getAllMovies(): LiveData<List<MovieEntity>>

    fun getMovieById(movieId: String): LiveData<MovieEntity>

    fun getAllTvShows(): LiveData<List<TvShowEntity>>

    fun getTvShowById(tvShowId: String): LiveData<TvShowEntity>

}