package com.fadhlansulistiyo.cinemacatalog.core.data.remote.source

import com.fadhlansulistiyo.cinemacatalog.core.data.remote.dto.DetailPeopleDTO
import com.fadhlansulistiyo.cinemacatalog.core.data.remote.dto.MultiCreditsMovieTvDTO
import com.fadhlansulistiyo.cinemacatalog.core.data.remote.network.ApiResponseResult
import com.fadhlansulistiyo.cinemacatalog.core.data.remote.network.ApiService
import com.fadhlansulistiyo.cinemacatalog.core.data.remote.dto.PeopleDTO
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PeopleRemoteDataSource @Inject constructor(
    private val apiService: ApiService
) : BaseRemoteDataSource() {

    suspend fun getTrendingPeople(): ApiResponseResult<List<PeopleDTO>> {
        return handleApiCall {
            apiService.getTrendingPeople().results
        }
    }

    private suspend fun getDetailPeople(peopleId: Int): ApiResponseResult<DetailPeopleDTO> {
        return handleApiCall {
            apiService.getDetailPeople(peopleId)
        }
    }

    private suspend fun getCredits(id: Int): ApiResponseResult<List<MultiCreditsMovieTvDTO>> {
        return handleApiCall {
            apiService.getCredits(id).cast
        }
    }

    suspend fun getDetailPeopleWithCredits(
        peopleId: Int
    ): Pair<ApiResponseResult<DetailPeopleDTO>, ApiResponseResult<List<MultiCreditsMovieTvDTO>>> {
        val detail = getDetailPeople(peopleId)
        val credits = getCredits(peopleId)
        return Pair(detail, credits)
    }
}