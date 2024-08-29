package com.fadhlansulistiyo.cinemacatalog.core.data.remote.dto

import com.google.gson.annotations.SerializedName

data class DetailTvDTO(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("number_of_episodes")
    val numberOfEpisodes: Int? = null,

    @field:SerializedName("backdrop_path")
    val backdropPath: String? = null,

    @field:SerializedName("genres")
    val genres: List<GenresDTO>? = emptyList(),

    @field:SerializedName("number_of_seasons")
    val numberOfSeasons: Int? = null,

    @field:SerializedName("first_air_date")
    val firstAirDate: String? = null,

    @field:SerializedName("overview")
    val overview: String? = null,

    @field:SerializedName("poster_path")
    val posterPath: String? = null,

    @field:SerializedName("production_companies")
    val productionCompanies: List<ProductionCompaniesDTO>? = emptyList(),

    @field:SerializedName("vote_average")
    val voteAverage: Double? = null,

    @field:SerializedName("name")
    val name: String? = null,

)