package com.fadhlansulistiyo.cinemacatalog.core.domain.usecase

import androidx.paging.PagingData
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.MultiSearch
import com.fadhlansulistiyo.cinemacatalog.core.domain.repository.ISearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchInteractor @Inject constructor(
    private val searchRepository: ISearchRepository
) : SearchUseCase {

    override fun getMultiSearch(query: String): Flow<PagingData<MultiSearch>> {
       return searchRepository.getMultiSearch(query)
    }
}