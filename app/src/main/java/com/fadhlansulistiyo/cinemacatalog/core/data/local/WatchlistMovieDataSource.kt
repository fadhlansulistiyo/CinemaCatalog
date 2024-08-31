package com.fadhlansulistiyo.cinemacatalog.core.data.local

import com.fadhlansulistiyo.cinemacatalog.core.data.local.db.WatchlistMovieDao
import com.fadhlansulistiyo.cinemacatalog.core.data.local.db.WatchlistMovieEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WatchlistMovieDataSource @Inject constructor(private val watchlistDao: WatchlistMovieDao) {
    suspend fun addWatchlist(watchlist: WatchlistMovieEntity) = watchlistDao.addWatchlist(watchlist)
    suspend fun removeWatchlist(watchlist: WatchlistMovieEntity) = watchlistDao.removeWatchlist(watchlist)
    suspend fun getWatchlistByTitle(title: String): WatchlistMovieEntity? = watchlistDao.getWatchlistByTitle(title)
    fun getAllWatchlist(): Flow<List<WatchlistMovieEntity>> = watchlistDao.getAllWatchlist()
}