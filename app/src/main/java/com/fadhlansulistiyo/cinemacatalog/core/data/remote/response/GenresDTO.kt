package com.fadhlansulistiyo.cinemacatalog.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class GenresDTO(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("name")
	val name: String? = null,

)
