package com.fadhlansulistiyo.cinemacatalog.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class ListTvDTO(

	@field:SerializedName("page")
	val page: Int,

	@field:SerializedName("total_pages")
	val totalPages: Int,

	@field:SerializedName("results")
	val results: List<TvDTO>,

	@field:SerializedName("total_results")
	val totalResults: Int

)

