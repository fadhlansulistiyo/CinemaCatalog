package com.fadhlansulistiyo.cinemacatalog.core.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val posterPath: String,
    val releaseDate: String,
    val voteAverage: Double,
    val backdropPath: String
)