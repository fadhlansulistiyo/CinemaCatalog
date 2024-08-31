package com.fadhlansulistiyo.cinemacatalog.core.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        WatchlistMovieEntity::class,
        WatchlistTvEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class CinemaDatabase : RoomDatabase() {

    abstract fun watchlistMovieDao(): WatchlistMovieDao
    abstract fun watchlistTvDao(): WatchlistTvDao

}