package com.fadhlansulistiyo.cinemacatalog.core.data.repository

import com.fadhlansulistiyo.cinemacatalog.core.data.local.WatchlistMovieDataSource
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.WatchlistMovie
import com.fadhlansulistiyo.cinemacatalog.core.domain.repository.IWatchlistMovieRepository
import com.fadhlansulistiyo.cinemacatalog.core.utils.mapper.toDomain
import com.fadhlansulistiyo.cinemacatalog.core.utils.mapper.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WatchlistMovieRepository @Inject constructor(
    private val watchlistMovieDataSource: WatchlistMovieDataSource
) : IWatchlistMovieRepository {

    override suspend fun addWatchlist(watchlistMovie: WatchlistMovie) {
        return watchlistMovieDataSource.addWatchlist(watchlistMovie.toEntity())
    }

    override suspend fun removeWatchlist(watchlistMovie: WatchlistMovie) {
        return watchlistMovieDataSource.removeWatchlist(watchlistMovie.toEntity())
    }

    override suspend fun getWatchlistByTitle(title: String): WatchlistMovie? {
        return watchlistMovieDataSource.getWatchlistByTitle(title)?.toDomain()
    }

    override fun getAllWatchlist(): Flow<List<WatchlistMovie>> {
        return watchlistMovieDataSource.getAllWatchlist().map { entities ->
            entities.map { it.toDomain() }
        }
    }
}