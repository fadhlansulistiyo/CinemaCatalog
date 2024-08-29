package com.fadhlansulistiyo.cinemacatalog.core.utils.mapper

import com.fadhlansulistiyo.cinemacatalog.core.data.remote.dto.CastDTO
import com.fadhlansulistiyo.cinemacatalog.core.data.remote.dto.DetailMovieDTO
import com.fadhlansulistiyo.cinemacatalog.core.data.remote.dto.DetailTvDTO
import com.fadhlansulistiyo.cinemacatalog.core.data.remote.dto.TvDTO
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.DetailMovie
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.DetailTv
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.MovieCast
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.Tv
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.TvCast
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.NA
import com.fadhlansulistiyo.cinemacatalog.core.utils.mapper.BaseMapper.mapGenresResponseToDomain
import com.fadhlansulistiyo.cinemacatalog.core.utils.mapper.BaseMapper.mapProductionCompaniesResponseToDomain

fun TvDTO.toDomainModel(): Tv {
    return Tv(
        id = this.id,
        name = this.name.orEmpty(),
        posterPath = this.posterPath.orEmpty(),
        firstAirDate = this.firstAirDate ?: NA,
        voteAverage = this.voteAverage ?: 0.0,
    )
}

fun DetailTvDTO.toDomainModel(): DetailTv {
    return DetailTv(
        id = this.id,
        numberOfEpisodes = this.numberOfEpisodes ?: 0,
        backdropPath = this.backdropPath ?: "",
        genres = this.genres?.map { mapGenresResponseToDomain(it) } ?: emptyList(),
        numberOfSeasons = this.numberOfSeasons ?: 0,
        firstAirDate = this.firstAirDate ?: "",
        overview = this.overview ?: NA,
        posterPath = this.posterPath ?: "",
        productionCompanies = this.productionCompanies?.map {
            mapProductionCompaniesResponseToDomain(it) } ?: emptyList(),
        voteAverage = this.voteAverage ?: 0.0,
        name = this.name ?: NA,
    )
}

fun CastDTO.toTvDomainModel(): TvCast {
    return TvCast(
        id = this.id,
        castId = this.castId ?: 0,
        name = this.name ?: NA,
        character = this.character ?: NA,
        profilePath = this.profilePath ?: "",
    )
}