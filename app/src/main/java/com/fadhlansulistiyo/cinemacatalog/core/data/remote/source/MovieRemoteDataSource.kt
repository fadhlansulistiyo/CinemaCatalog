package com.fadhlansulistiyo.cinemacatalog.core.data.remote.source

import com.fadhlansulistiyo.cinemacatalog.core.data.remote.network.ApiResponseResult
import com.fadhlansulistiyo.cinemacatalog.core.data.remote.network.ApiService
import com.fadhlansulistiyo.cinemacatalog.core.data.remote.response.CastDTO
import com.fadhlansulistiyo.cinemacatalog.core.data.remote.response.DetailMovieDTO
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRemoteDataSource @Inject constructor(
    private val apiService: ApiService
) : BaseRemoteDataSource() {
    private suspend fun getDetailMovie(movieId: Int): ApiResponseResult<DetailMovieDTO> {
        return handleApiCall {
            apiService.getDetailMovie(movieId)
        }
    }

    private suspend fun getCast(movieId: Int): ApiResponseResult<List<CastDTO>> {
        return handleApiCall {
            apiService.getMovieCredits(movieId).cast
        }
    }

    suspend fun getDetailMovieWithCast(
        movieId: Int
    ): Pair<ApiResponseResult<DetailMovieDTO>, ApiResponseResult<List<CastDTO>>> {
        val detail = getDetailMovie(movieId)
        val cast = getCast(movieId)
        return Pair(detail, cast)
    }
}