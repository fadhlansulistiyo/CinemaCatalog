package com.fadhlansulistiyo.cinemacatalog.core.domain.usecase

import androidx.paging.PagingData
import com.fadhlansulistiyo.cinemacatalog.core.data.Resource
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.DetailMovieWithCast
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getNowPlaying(): Flow<PagingData<Movie>>
    suspend fun getDetailMovie(movieId: Int): Resource<DetailMovieWithCast>
}