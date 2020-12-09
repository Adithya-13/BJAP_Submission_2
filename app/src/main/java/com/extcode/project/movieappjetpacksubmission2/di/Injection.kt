package com.extcode.project.movieappjetpacksubmission2.di

import com.extcode.project.movieappjetpacksubmission2.data.MovieAppRepository
import com.extcode.project.movieappjetpacksubmission2.data.source.remote.RemoteDataSource

object Injection {

    fun provideRepository(): MovieAppRepository {

        val remoteRepository = RemoteDataSource.getInstance()

        return MovieAppRepository.getInstance(remoteRepository)
    }
}