package com.fadhlansulistiyo.cinemacatalog.core.utils.mapper

import com.fadhlansulistiyo.cinemacatalog.core.data.local.db.WatchlistMovieEntity
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.WatchlistMovie

fun WatchlistMovie.toEntity(): WatchlistMovieEntity {
    return WatchlistMovieEntity(
        id = this.id,
        title = this.title,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        voteAverage = this.voteAverage
    )
}

fun WatchlistMovieEntity.toDomain(): WatchlistMovie {
    return WatchlistMovie(
        id = this.id,
        title = this.title,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        voteAverage = this.voteAverage
    )
}