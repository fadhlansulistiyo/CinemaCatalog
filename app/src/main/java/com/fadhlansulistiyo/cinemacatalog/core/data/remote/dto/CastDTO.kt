package com.fadhlansulistiyo.cinemacatalog.core.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CastDTO(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("cast_id")
    val castId: Int? = null,

    @field:SerializedName("character")
    val character: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("profile_path")
    val profilePath: String? = null

)