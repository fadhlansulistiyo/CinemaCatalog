package com.fadhlansulistiyo.cinemacatalog.core.data.repository

import com.fadhlansulistiyo.cinemacatalog.core.data.local.WatchlistTvDataSource
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.WatchlistTv
import com.fadhlansulistiyo.cinemacatalog.core.domain.repository.IWatchlistTvRepository
import com.fadhlansulistiyo.cinemacatalog.core.utils.mapper.toDomain
import com.fadhlansulistiyo.cinemacatalog.core.utils.mapper.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WatchlistTvRepository @Inject constructor(
    private val watchlistTvDataSource: WatchlistTvDataSource
) : IWatchlistTvRepository {

    override suspend fun addWatchlist(watchlistTv: WatchlistTv) {
        return watchlistTvDataSource.addWatchlist(watchlistTv.toEntity())
    }

    override suspend fun removeWatchlist(watchlistTv: WatchlistTv) {
        return watchlistTvDataSource.removeWatchlist(watchlistTv.toEntity())
    }

    override suspend fun getWatchlistByTitle(title: String): WatchlistTv? {
        return watchlistTvDataSource.getWatchlistByTitle(title)?.toDomain()
    }

    override fun getAllWatchlist(): Flow<List<WatchlistTv>> {
        return watchlistTvDataSource.getAllWatchlist().map { entities ->
            entities.map { it.toDomain() }
        }
    }
}