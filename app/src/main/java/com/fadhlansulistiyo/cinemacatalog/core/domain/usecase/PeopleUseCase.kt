package com.fadhlansulistiyo.cinemacatalog.core.domain.usecase

import com.fadhlansulistiyo.cinemacatalog.core.data.Resource
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.DetailPeopleWithCredits
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.People
import kotlinx.coroutines.flow.Flow

interface PeopleUseCase {
    fun getTrendingPeople(): Flow<Resource<List<People>>>
    suspend fun getDetailPeople(peopleId: Int): Resource<DetailPeopleWithCredits>
}