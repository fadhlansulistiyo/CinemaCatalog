package com.fadhlansulistiyo.cinemacatalog.core.domain.model

data class DetailTvWithCast(
    val detail: DetailTv,
    val cast: List<TvCast>
)