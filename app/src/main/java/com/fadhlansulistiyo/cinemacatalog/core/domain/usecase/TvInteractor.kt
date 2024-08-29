package com.fadhlansulistiyo.cinemacatalog.core.domain.usecase

import com.fadhlansulistiyo.cinemacatalog.core.data.Resource
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.DetailTv
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.DetailTvWithCast
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.Tv
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.TvCast
import com.fadhlansulistiyo.cinemacatalog.core.domain.repository.ITvRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TvInteractor @Inject constructor(private val tvRepository: ITvRepository) : TvUseCase {

    override fun getTopRatedTv(): Flow<Resource<List<Tv>>> {
        return tvRepository.getTopRatedTv()
    }

    override suspend fun getDetailTv(tvId: Int): Resource<DetailTvWithCast> {
        return tvRepository.getDetailTv(tvId)
    }
}