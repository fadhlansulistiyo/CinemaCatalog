package com.fadhlansulistiyo.cinemacatalog.core.utils.mapper

import com.fadhlansulistiyo.cinemacatalog.core.data.remote.response.MovieDTO
import com.fadhlansulistiyo.cinemacatalog.core.data.remote.response.TvDTO
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.Movie
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.Tv
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.NA

fun TvDTO.toDomainModel(): Tv {
    return Tv(
        id = this.id,
        name = this.name.orEmpty(),
        posterPath = this.posterPath.orEmpty(),
        firstAirDate = this.firstAirDate ?: NA,
        voteAverage = this.voteAverage ?: 0.0,
    )
}