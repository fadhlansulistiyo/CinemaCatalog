package com.fadhlansulistiyo.cinemacatalog.core.utils.mapper

import com.fadhlansulistiyo.cinemacatalog.core.data.remote.dto.CastDTO
import com.fadhlansulistiyo.cinemacatalog.core.data.remote.dto.DetailMovieDTO
import com.fadhlansulistiyo.cinemacatalog.core.data.remote.dto.MovieDTO
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.DetailMovie
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.DetailMovieWithCast
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.Movie
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.MovieCast
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.WatchlistMovie
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.NA
import com.fadhlansulistiyo.cinemacatalog.core.utils.mapper.BaseMapper.mapGenresResponseToDomain
import com.fadhlansulistiyo.cinemacatalog.core.utils.mapper.BaseMapper.mapProductionCompaniesResponseToDomain

fun MovieDTO.toDomainModel(): Movie {
    return Movie(
        id = this.id,
        title = this.title.orEmpty(),
        posterPath = this.posterPath.orEmpty(),
        releaseDate = this.releaseDate ?: NA,
        voteAverage = this.voteAverage ?: 0.0,
        backdropPath = this.backdropPath.orEmpty()
    )
}

fun DetailMovieDTO.toDomainModel(): DetailMovie {
    return DetailMovie(
        id = this.id,
        title = this.title ?: NA,
        overview = this.overview ?: NA,
        runtime = this.runtime ?: 0,
        backdropPath = this.backdropPath ?: "",
        releaseDate = this.releaseDate ?: "",
        genres = this.genres?.map { mapGenresResponseToDomain(it) } ?: emptyList(),
        posterPath = this.posterPath ?: "",
        productionCompanies = this.productionCompanies?.map { mapProductionCompaniesResponseToDomain(it) } ?: emptyList(),
        voteAverage = this.voteAverage ?: 0.0,
    )
}

fun CastDTO.toMovieDomainModel(): MovieCast {
    return MovieCast(
        id = this.id,
        castId = this.castId ?: 0,
        name = this.name ?: NA,
        character = this.character ?: NA,
        profilePath = this.profilePath ?: "",
    )
}

fun DetailMovieWithCast.toWatchlistMovieModel(): WatchlistMovie {
    return WatchlistMovie(
        id = this.detail.id,
        title = this.detail.title,
        posterPath = this.detail.posterPath,
        releaseDate = this.detail.releaseDate,
        voteAverage = this.detail.voteAverage
    )
}