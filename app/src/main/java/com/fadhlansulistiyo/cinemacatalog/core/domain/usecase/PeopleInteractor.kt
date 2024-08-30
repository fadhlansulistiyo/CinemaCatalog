package com.fadhlansulistiyo.cinemacatalog.core.domain.usecase

import com.fadhlansulistiyo.cinemacatalog.core.data.Resource
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.DetailPeopleWithCredits
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.People
import com.fadhlansulistiyo.cinemacatalog.core.domain.repository.IPeopleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PeopleInteractor @Inject constructor(
    private val peopleRepository: IPeopleRepository
) : PeopleUseCase {

    override fun getTrendingPeople(): Flow<Resource<List<People>>> {
        return peopleRepository.getTrendingPeople()
    }

    override suspend fun getDetailPeople(peopleId: Int): Resource<DetailPeopleWithCredits> {
        return peopleRepository.getDetailPeople(peopleId)
    }
}