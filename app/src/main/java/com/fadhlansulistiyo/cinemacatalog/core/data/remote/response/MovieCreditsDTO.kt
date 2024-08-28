package com.fadhlansulistiyo.cinemacatalog.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class MovieCreditsDTO(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("cast")
	val cast: List<CastDTO>

)