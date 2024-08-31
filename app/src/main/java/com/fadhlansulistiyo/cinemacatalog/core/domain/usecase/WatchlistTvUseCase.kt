package com.fadhlansulistiyo.cinemacatalog.core.domain.usecase

import com.fadhlansulistiyo.cinemacatalog.core.domain.model.WatchlistTv
import kotlinx.coroutines.flow.Flow

interface WatchlistTvUseCase {
    suspend fun addWatchlist(watchlistTv: WatchlistTv)
    suspend fun removeWatchlist(watchlistTv: WatchlistTv)
    suspend fun getWatchlistByTitle(title: String): WatchlistTv?
    fun getAllWatchlist(): Flow<List<WatchlistTv>>
}