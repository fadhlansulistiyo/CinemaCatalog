package com.fadhlansulistiyo.cinemacatalog.core.domain.repository

import androidx.paging.PagingData
import com.fadhlansulistiyo.cinemacatalog.core.data.Resource
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.DetailMovieWithCast
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getNowPlaying(): Flow<PagingData<Movie>>
    suspend fun getDetailMovie(movieId: Int): Resource<DetailMovieWithCast>
}