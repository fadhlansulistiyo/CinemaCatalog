package com.fadhlansulistiyo.cinemacatalog.core.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TvCreditsDTO(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("cast")
    val cast: List<CastDTO>

)