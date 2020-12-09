package com.extcode.project.movieappjetpacksubmission2.data.source.local.entity

data class MovieEntity(
    val overview: String,
    val originalLanguage: String,
    val releaseDate: String,
    val popularity: Double,
    val voteAverage: Double,
    val id: Int,
    val title: String,
    val voteCount: Int,
    val posterPath: String
)
