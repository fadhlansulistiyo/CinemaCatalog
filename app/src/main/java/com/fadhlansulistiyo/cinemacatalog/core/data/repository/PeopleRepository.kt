package com.fadhlansulistiyo.cinemacatalog.core.data.repository

import com.fadhlansulistiyo.cinemacatalog.core.data.Resource
import com.fadhlansulistiyo.cinemacatalog.core.data.remote.network.ApiResponseResult
import com.fadhlansulistiyo.cinemacatalog.core.data.remote.source.PeopleRemoteDataSource
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.DetailPeopleWithCredits
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.People
import com.fadhlansulistiyo.cinemacatalog.core.domain.repository.IPeopleRepository
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.DATA_IS_EMPTY
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.UNKNOWN_ERROR
import com.fadhlansulistiyo.cinemacatalog.core.utils.mapper.toDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PeopleRepository @Inject constructor(
    private val remoteDataSource: PeopleRemoteDataSource
) : IPeopleRepository {

    override fun getTrendingPeople(): Flow<Resource<List<People>>> = flow {
        emit(Resource.Loading())
        try {
            when (val response = remoteDataSource.getTrendingPeople()) {
                is ApiResponseResult.Success -> {
                    val data = response.data.map { item ->
                        item.toDomainModel()
                    }
                    emit(Resource.Success(data))
                }

                is ApiResponseResult.Error -> {
                    emit(Resource.Error(response.errorMessage))
                }

                is ApiResponseResult.Empty -> {
                    emit(Resource.Error(DATA_IS_EMPTY))
                }
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.toString()))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getDetailPeople(peopleId: Int): Resource<DetailPeopleWithCredits> {
        return try {
            val (detailResponse, creditsResponse) =
                remoteDataSource.getDetailPeopleWithCredits(peopleId)

            when {
                detailResponse is ApiResponseResult.Success && creditsResponse is ApiResponseResult.Success -> {
                    val detail = detailResponse.data.toDomainModel()
                    val credits = creditsResponse.data.map { credit ->
                        credit.toDomainModel()
                    }.filter { it.releaseDate.isNotEmpty() }
                        .sortedByDescending { it.releaseDate }
                    Resource.Success(DetailPeopleWithCredits(detail, credits))
                }

                detailResponse is ApiResponseResult.Error -> {
                    Resource.Error(detailResponse.errorMessage)
                }

                creditsResponse is ApiResponseResult.Error -> {
                    Resource.Error(creditsResponse.errorMessage)
                }

                else -> {
                    Resource.Error(UNKNOWN_ERROR)
                }
            }
        } catch (e: Exception) {
            Resource.Error(e.toString())
        }
    }
}