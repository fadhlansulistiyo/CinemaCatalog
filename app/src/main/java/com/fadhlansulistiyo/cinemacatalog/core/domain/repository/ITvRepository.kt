package com.fadhlansulistiyo.cinemacatalog.core.domain.repository

import com.fadhlansulistiyo.cinemacatalog.core.data.Resource
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.Tv
import kotlinx.coroutines.flow.Flow

interface ITvRepository {
    fun getTopRatedTv(): Flow<Resource<List<Tv>>>
    // fun getCast(seriesId: Int): Flow<Resource<List<TvCast>>>
    // suspend fun getDetailTv(seriesId: Int): Resource<DetailTv>
}