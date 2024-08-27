package com.fadhlansulistiyo.cinemacatalog.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class MovieCreditsResponse(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("cast")
	val cast: List<CastResponse>

)