package com.extcode.project.movieappjetpacksubmission2.data.source.local.entity

data class TvShowEntity(
    val firstAirDate: String,
    val overview: String,
    val originalLanguage: String,
    val popularity: Double,
    val voteAverage: Double,
    val name: String,
    val id: Int,
    val voteCount: Int,
    val posterPath: String,
)
