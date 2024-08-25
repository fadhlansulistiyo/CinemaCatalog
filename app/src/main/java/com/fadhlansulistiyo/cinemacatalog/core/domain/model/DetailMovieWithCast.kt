package com.fadhlansulistiyo.cinemacatalog.core.domain.model

import com.fadhlansulistiyo.cinemadatabase.core.domain.model.DetailMovie

data class DetailMovieWithCast(
    val detail: DetailMovie,
    val cast: List<MovieCast>
)