package com.fadhlansulistiyo.cinemacatalog.core.domain.model

data class WatchlistMovie(
    val id: Int,
    val title: String,
    val posterPath: String,
    val releaseDate: String,
    val voteAverage: Double
)