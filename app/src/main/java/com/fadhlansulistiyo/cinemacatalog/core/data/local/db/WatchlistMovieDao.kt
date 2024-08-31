package com.fadhlansulistiyo.cinemacatalog.core.data.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WatchlistMovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addWatchlist(watchlist: WatchlistMovieEntity)

    @Delete
    suspend fun removeWatchlist(watchlist: WatchlistMovieEntity)

    @Query("SELECT * FROM watchlist_movie WHERE title = :title LIMIT 1")
    suspend fun getWatchlistByTitle(title: String): WatchlistMovieEntity?

    @Query("SELECT * FROM watchlist_movie")
    fun getAllWatchlist(): Flow<List<WatchlistMovieEntity>>
}