package com.fadhlansulistiyo.cinemacatalog.core.di

import android.content.Context
import androidx.room.Room
import com.fadhlansulistiyo.cinemacatalog.core.data.local.db.CinemaDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): CinemaDatabase {
        return Room.databaseBuilder(
            context,
            CinemaDatabase::class.java, "Cinlog.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideWatchlistMovieDao(database: CinemaDatabase) = database.watchlistMovieDao()

    @Provides
    fun provideWatchlistTvDao(database: CinemaDatabase) = database.watchlistTvDao()
}