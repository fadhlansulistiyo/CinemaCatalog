package com.fadhlansulistiyo.cinemacatalog.core.domain.model

data class Tv(
    val id: Int,
    val name: String,
    val posterPath: String,
    val firstAirDate: String,
    val voteAverage: Double
)