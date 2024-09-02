package com.fadhlansulistiyo.cinemacatalog.core.domain.repository

import androidx.paging.PagingData
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.MultiSearch
import kotlinx.coroutines.flow.Flow

interface ISearchRepository {
    fun getMultiSearch(query: String): Flow<PagingData<MultiSearch>>
}