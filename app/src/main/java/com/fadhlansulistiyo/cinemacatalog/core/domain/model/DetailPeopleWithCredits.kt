package com.fadhlansulistiyo.cinemacatalog.core.domain.model

data class DetailPeopleWithCredits(
    val detail: DetailPeople,
    val credits: List<MultiCreditsMovieTv>
)