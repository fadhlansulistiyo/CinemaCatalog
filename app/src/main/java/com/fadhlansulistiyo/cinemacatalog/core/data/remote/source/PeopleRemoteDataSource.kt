package com.fadhlansulistiyo.cinemacatalog.core.data.remote.source

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
}