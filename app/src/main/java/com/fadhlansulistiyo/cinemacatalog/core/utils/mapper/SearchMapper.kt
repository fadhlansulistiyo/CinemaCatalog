package com.fadhlansulistiyo.cinemacatalog.core.utils.mapper

import com.fadhlansulistiyo.cinemacatalog.core.data.remote.dto.MultiSearchDTO
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.MultiSearch
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.NA

fun MultiSearchDTO.toDomainModel() : MultiSearch {
    return MultiSearch(
        id = this.id,
        title = this.title ?: NA,
        posterPath = this.posterPath ?: "",
        releaseDate = this.releaseDate ?: "",
        voteAverage = this.voteAverage ?: 0.0,
        mediaType = this.mediaType ?: NA,
        overview = this.overview ?: NA
    )
}
