package com.fadhlansulistiyo.cinemacatalog.core.domain.repository

import com.fadhlansulistiyo.cinemacatalog.core.data.Resource
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.DetailTvWithCast
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.Tv
import kotlinx.coroutines.flow.Flow

interface ITvRepository {
    fun getTopRatedTv(): Flow<Resource<List<Tv>>>
    suspend fun getDetailTv(tvId: Int): Resource<DetailTvWithCast>
}