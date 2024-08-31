package com.fadhlansulistiyo.cinemacatalog.core.data.local

import com.fadhlansulistiyo.cinemacatalog.core.data.local.db.WatchlistTvDao
import com.fadhlansulistiyo.cinemacatalog.core.data.local.db.WatchlistTvEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WatchlistTvDataSource @Inject constructor(private val watchlistDao: WatchlistTvDao) {
    suspend fun addWatchlist(watchlist: WatchlistTvEntity) = watchlistDao.addWatchlist(watchlist)
    suspend fun removeWatchlist(watchlist: WatchlistTvEntity) = watchlistDao.removeWatchlist(watchlist)
    suspend fun getWatchlistByTitle(title: String): WatchlistTvEntity? = watchlistDao.getWatchlistByTitle(title)
    fun getAllWatchlist(): Flow<List<WatchlistTvEntity>> = watchlistDao.getAllWatchlist()
}