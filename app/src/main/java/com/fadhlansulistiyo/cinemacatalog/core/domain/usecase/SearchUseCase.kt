package com.fadhlansulistiyo.cinemacatalog.core.domain.usecase

import androidx.paging.PagingData
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.MultiSearch
import kotlinx.coroutines.flow.Flow

interface SearchUseCase {
    fun getMultiSearch(query: String): Flow<PagingData<MultiSearch>>
}