package com.fadhlansulistiyo.cinemacatalog.core.domain.repository

import androidx.paging.PagingData
import com.fadhlansulistiyo.cinemacatalog.core.data.Resource
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.People
import kotlinx.coroutines.flow.Flow

interface IPeopleRepository {
    fun getTrendingPeople(): Flow<Resource<List<People>>>
    /*suspend fun getDetailPeople(peopleId: Int): Resource<DetailPeople>
    fun getPopularPeople(): Flow<PagingData<PopularPeople>>
    fun getCredits(id: Int): Flow<Resource<List<MultiCreditsMovieTv>>>*/
}