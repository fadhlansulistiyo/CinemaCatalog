package com.fadhlansulistiyo.cinemacatalog.ui.screen.watchlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.WatchlistMovie
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.WatchlistTv
import com.fadhlansulistiyo.cinemacatalog.core.domain.usecase.WatchlistMovieUseCase
import com.fadhlansulistiyo.cinemacatalog.core.domain.usecase.WatchlistTvUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class WatchlistViewModel @Inject constructor(
    watchListMovieUseCase: WatchlistMovieUseCase,
    watchListTvUseCase: WatchlistTvUseCase
) : ViewModel() {

    val movieWatchlist: StateFlow<List<WatchlistMovie>> =
        watchListMovieUseCase.getAllWatchlist().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val tvWatchlist: StateFlow<List<WatchlistTv>> =
        watchListTvUseCase.getAllWatchlist().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
}