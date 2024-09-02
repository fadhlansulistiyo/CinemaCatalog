package com.fadhlansulistiyo.cinemacatalog.core.data.remote.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fadhlansulistiyo.cinemacatalog.core.data.remote.network.ApiService
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.MultiSearch
import com.fadhlansulistiyo.cinemacatalog.core.utils.mapper.toDomainModel

class PagingSearch(
    private val apiService: ApiService,
    private var searchParam: String
) : PagingSource<Int, MultiSearch>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MultiSearch> {
        return try {
            val page = params.key ?: 1
            val response = apiService.getMultiSearch(query = searchParam, page = page)
            val results = response.results.map { searchItem ->
                searchItem.toDomainModel()
            }
            LoadResult.Page(
                data = results,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.totalPages > page) page + 1 else null
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MultiSearch>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}