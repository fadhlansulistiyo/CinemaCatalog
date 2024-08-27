package com.fadhlansulistiyo.cinemacatalog.ui.screen.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.Movie
import com.fadhlansulistiyo.cinemacatalog.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    movieUseCase: MovieUseCase,
) : ViewModel() {

    // get now playing
    // Now Playing movies exposed as PagingData flow
    val nowPlayingMovies: Flow<PagingData<Movie>> = movieUseCase.getNowPlaying()
        .cachedIn(viewModelScope)
}