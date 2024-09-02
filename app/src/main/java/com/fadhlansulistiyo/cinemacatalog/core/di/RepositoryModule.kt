package com.fadhlansulistiyo.cinemacatalog.core.di

import com.fadhlansulistiyo.cinemacatalog.core.data.repository.MovieRepository
import com.fadhlansulistiyo.cinemacatalog.core.data.repository.PeopleRepository
import com.fadhlansulistiyo.cinemacatalog.core.data.repository.SearchRepository
import com.fadhlansulistiyo.cinemacatalog.core.data.repository.TvRepository
import com.fadhlansulistiyo.cinemacatalog.core.data.repository.WatchlistMovieRepository
import com.fadhlansulistiyo.cinemacatalog.core.data.repository.WatchlistTvRepository
import com.fadhlansulistiyo.cinemacatalog.core.domain.repository.IMovieRepository
import com.fadhlansulistiyo.cinemacatalog.core.domain.repository.IPeopleRepository
import com.fadhlansulistiyo.cinemacatalog.core.domain.repository.ISearchRepository
import com.fadhlansulistiyo.cinemacatalog.core.domain.repository.ITvRepository
import com.fadhlansulistiyo.cinemacatalog.core.domain.repository.IWatchlistMovieRepository
import com.fadhlansulistiyo.cinemacatalog.core.domain.repository.IWatchlistTvRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(
    includes = [
        NetworkModule::class,
        DatabaseModule::class
    ]
)
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideMovieRepository(movieRepository: MovieRepository): IMovieRepository

    @Binds
    abstract fun provideTvRepository(tvRepository: TvRepository): ITvRepository

    @Binds
    abstract fun providePeopleRepository(peopleRepository: PeopleRepository): IPeopleRepository

    @Binds
    abstract fun provideWatchlistMovieRepository(watchlistRepository: WatchlistMovieRepository): IWatchlistMovieRepository

    @Binds
    abstract fun provideWatchlistTvRepository(watchlistRepository: WatchlistTvRepository): IWatchlistTvRepository

     @Binds
     abstract fun provideSearchRepository(searchRepository: SearchRepository): ISearchRepository
}