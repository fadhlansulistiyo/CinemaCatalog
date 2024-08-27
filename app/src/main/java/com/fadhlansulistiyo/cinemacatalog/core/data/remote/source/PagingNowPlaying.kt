package com.fadhlansulistiyo.cinemacatalog.core.data.remote.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fadhlansulistiyo.cinemacatalog.core.data.remote.network.ApiService
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.Movie
import com.fadhlansulistiyo.cinemacatalog.core.utils.mapper.toDomainModel

class PagingNowPlaying(
    private val apiService: ApiService
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val page = params.key ?: 1
            val response = apiService.getNowPlaying(page = page)
            val results = response.results.map { movieResponse ->
                movieResponse.toDomainModel()
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

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}