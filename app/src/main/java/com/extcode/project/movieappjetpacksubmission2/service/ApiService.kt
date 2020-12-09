package com.extcode.project.movieappjetpacksubmission2.service

import com.extcode.project.movieappjetpacksubmission2.BuildConfig
import com.extcode.project.movieappjetpacksubmission2.data.source.remote.response.MoviesResponse
import com.extcode.project.movieappjetpacksubmission2.data.source.remote.response.TvShowsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie")
    fun getMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Call<MoviesResponse>

    @GET("tv?api_key=${BuildConfig.API_KEY}")
    fun getTvShows(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Call<TvShowsResponse>

}