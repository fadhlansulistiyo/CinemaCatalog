package com.fadhlansulistiyo.cinemacatalog.core.utils.mapper

import com.fadhlansulistiyo.cinemacatalog.core.data.remote.dto.DetailPeopleDTO
import com.fadhlansulistiyo.cinemacatalog.core.data.remote.dto.MultiCreditsMovieTvDTO
import com.fadhlansulistiyo.cinemacatalog.core.data.remote.dto.PeopleDTO
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.DetailPeople
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.MultiCreditsMovieTv
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.People
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.NA
import java.text.SimpleDateFormat
import java.util.Locale

fun PeopleDTO.toDomainModel(): People {
    return People(
        id = this.id,
        name = this.name.orEmpty(),
        profilePath = this.profilePath ?: ""
    )
}

fun DetailPeopleDTO.toDomainModel(): DetailPeople {
    return DetailPeople(
        id = this.id,
        name = this.name ?: NA,
        birthday = this.birthday ?: "",
        knownForDepartment = this.knownForDepartment ?: NA,
        profilePath = this.profilePath ?: "",
        biography = this.biography ?: NA,
        placeOfBirth = this.placeOfBirth ?: NA,
    )
}

fun MultiCreditsMovieTvDTO.toDomainModel() : MultiCreditsMovieTv {
    val releaseDate = this.releaseDate?.takeIf { it.isNotEmpty() }?.let {
        val thisFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = thisFormat.parse(it)
        date?.let { outputFormat.format(date) } ?: it
    } ?: ""

    return MultiCreditsMovieTv(
        id = this.id,
        posterPath = this.posterPath ?: "",
        mediaType = this.mediaType ?: NA,
        voteAverage = this.voteAverage ?: 0.0,
        title = this.title ?: NA,
        releaseDate = releaseDate
    )
}
