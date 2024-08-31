package com.fadhlansulistiyo.cinemacatalog.core.utils.mapper

import com.fadhlansulistiyo.cinemacatalog.core.data.local.db.WatchlistTvEntity
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.WatchlistTv

fun WatchlistTv.toEntity(): WatchlistTvEntity {
    return WatchlistTvEntity(
        id = this.id,
        name = this.name,
        posterPath = this.posterPath,
        firstAirDate = this.firstAirDate,
        voteAverage = this.voteAverage
    )
}

fun WatchlistTvEntity.toDomain(): WatchlistTv {
    return WatchlistTv(
        id = this.id,
        name = this.name,
        posterPath = this.posterPath,
        firstAirDate = this.firstAirDate,
        voteAverage = this.voteAverage
    )
}