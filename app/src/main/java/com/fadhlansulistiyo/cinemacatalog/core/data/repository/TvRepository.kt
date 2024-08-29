package com.fadhlansulistiyo.cinemacatalog.core.data.repository

import com.fadhlansulistiyo.cinemacatalog.core.data.Resource
import com.fadhlansulistiyo.cinemacatalog.core.data.remote.network.ApiResponseResult
import com.fadhlansulistiyo.cinemacatalog.core.data.remote.source.TvRemoteDataSource
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.DetailTvWithCast
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.Tv
import com.fadhlansulistiyo.cinemacatalog.core.domain.repository.ITvRepository
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.DATA_IS_EMPTY
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.UNKNOWN_ERROR
import com.fadhlansulistiyo.cinemacatalog.core.utils.mapper.toDomainModel
import com.fadhlansulistiyo.cinemacatalog.core.utils.mapper.toTvDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TvRepository @Inject constructor(
    private val remoteDataSource: TvRemoteDataSource
) : ITvRepository {

    override fun getTopRatedTv(): Flow<Resource<List<Tv>>> = flow {
        emit(Resource.Loading())
        try {
            when (val response = remoteDataSource.getTopRatedTv()) {
                is ApiResponseResult.Success -> {
                    val data = response.data.map { tvItem ->
                        tvItem.toDomainModel()
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

    override suspend fun getDetailTv(tvId: Int): Resource<DetailTvWithCast> {
        return try {
            val (detailResponse, castResponse) = remoteDataSource.getDetailTvWithCast(tvId)

            when {
                detailResponse is ApiResponseResult.Success && castResponse is ApiResponseResult.Success -> {
                    val detail = detailResponse.data.toDomainModel()
                    val cast = castResponse.data.map {
                        it.toTvDomainModel()
                    }
                    Resource.Success(DetailTvWithCast(detail, cast))
                }

                detailResponse is ApiResponseResult.Error -> {
                    Resource.Error(detailResponse.errorMessage)
                }

                castResponse is ApiResponseResult.Error -> {
                    Resource.Error(castResponse.errorMessage)
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