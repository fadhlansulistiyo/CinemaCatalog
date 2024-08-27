package com.fadhlansulistiyo.cinemacatalog.ui.di

import com.fadhlansulistiyo.cinemacatalog.core.domain.usecase.MovieInteractor
import com.fadhlansulistiyo.cinemacatalog.core.domain.usecase.MovieUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {

    @Binds
    @ViewModelScoped
    abstract fun provideMovieUseCase(movieInteractor: MovieInteractor): MovieUseCase
}