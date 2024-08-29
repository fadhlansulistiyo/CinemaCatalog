package com.fadhlansulistiyo.cinemacatalog.core.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.fadhlansulistiyo.cinemacatalog.core.data.Resource
import com.fadhlansulistiyo.cinemacatalog.core.data.remote.network.ApiResponseResult
import com.fadhlansulistiyo.cinemacatalog.core.data.remote.network.ApiService
import com.fadhlansulistiyo.cinemacatalog.core.data.remote.source.MovieRemoteDataSource
import com.fadhlansulistiyo.cinemacatalog.core.data.remote.source.PagingNowPlaying
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.DetailMovieWithCast
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.Movie
import com.fadhlansulistiyo.cinemacatalog.core.domain.repository.IMovieRepository
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.UNKNOWN_ERROR
import com.fadhlansulistiyo.cinemacatalog.core.utils.mapper.toDomainModel
import com.fadhlansulistiyo.cinemacatalog.core.utils.mapper.toMovieDomainModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val apiService: ApiService,
    private val remoteDataSource: MovieRemoteDataSource
) : IMovieRepository {

    override fun getNowPlaying(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PagingNowPlaying(apiService)
            }
        ).flow
    }


    override suspend fun getDetailMovie(movieId: Int): Resource<DetailMovieWithCast> {
        return try {
            val (detailResponse, castResponse) = remoteDataSource.getDetailMovieWithCast(movieId)

            when {
                detailResponse is ApiResponseResult.Success && castResponse is ApiResponseResult.Success -> {
                    val detail = detailResponse.data.toDomainModel()
                    val cast = castResponse.data.map {
                        it.toMovieDomainModel()
                    }
                    Resource.Success(DetailMovieWithCast(detail, cast))
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