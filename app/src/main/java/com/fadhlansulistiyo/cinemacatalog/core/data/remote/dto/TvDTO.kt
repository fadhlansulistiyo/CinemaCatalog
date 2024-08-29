package com.fadhlansulistiyo.cinemacatalog.core.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TvDTO(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("poster_path")
    val posterPath: String? = null,

    @field:SerializedName("first_air_date")
    val firstAirDate: String? = null,

    @field:SerializedName("vote_average")
    val voteAverage: Double? = null,

)
