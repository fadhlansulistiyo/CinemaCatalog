package com.fadhlansulistiyo.cinemacatalog.core.data.remote.source

import com.fadhlansulistiyo.cinemacatalog.core.data.remote.dto.CastDTO
import com.fadhlansulistiyo.cinemacatalog.core.data.remote.dto.DetailMovieDTO
import com.fadhlansulistiyo.cinemacatalog.core.data.remote.dto.DetailTvDTO
import com.fadhlansulistiyo.cinemacatalog.core.data.remote.network.ApiResponseResult
import com.fadhlansulistiyo.cinemacatalog.core.data.remote.network.ApiService
import com.fadhlansulistiyo.cinemacatalog.core.data.remote.dto.TvDTO
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TvRemoteDataSource @Inject constructor(
    private val apiService: ApiService
) : BaseRemoteDataSource() {

    suspend fun getTopRatedTv(): ApiResponseResult<List<TvDTO>> {
        return handleApiCall {
            apiService.getTopRatedTv().results
        }
    }

    private suspend fun getDetailTv(tvId: Int): ApiResponseResult<DetailTvDTO> {
        return handleApiCall {
            apiService.getDetailTv(tvId)
        }
    }

    private suspend fun getCast(tvId: Int): ApiResponseResult<List<CastDTO>> {
        return handleApiCall {
            apiService.getTvCredits(tvId).cast
        }
    }

    suspend fun getDetailTvWithCast(
        tvId: Int
    ): Pair<ApiResponseResult<DetailTvDTO>, ApiResponseResult<List<CastDTO>>> {
        val detail = getDetailTv(tvId)
        val cast = getCast(tvId)
        return Pair(detail, cast)
    }
}