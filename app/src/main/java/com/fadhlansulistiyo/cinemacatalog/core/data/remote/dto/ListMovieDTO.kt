package com.fadhlansulistiyo.cinemacatalog.core.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ListMovieDTO(

    @field:SerializedName("page")
	val page: Int,

    @field:SerializedName("total_pages")
	val totalPages: Int,

    @field:SerializedName("results")
	val results: List<MovieDTO>,

    @field:SerializedName("total_results")
	val totalResults: Int

)

