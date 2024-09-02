package com.fadhlansulistiyo.cinemacatalog.ui.screen.watchlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.WatchlistMovie
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.WatchlistTv
import androidx.compose.foundation.lazy.grid.items
import com.fadhlansulistiyo.cinemacatalog.ui.components.EmptyState

@Composable
fun WatchlistMovieTab(
    movies: List<WatchlistMovie>,
    navigateToMovieDetail: (Int) -> Unit
) {
    if (movies.isEmpty()) {
        EmptyState()
    } else {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(135.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(12.dp),
        ) {
            items(movies) { movie ->
                WatchlistMovieItem(
                    movies = movie,
                    modifier = Modifier
                        .clickable {
                            navigateToMovieDetail(movie.id)
                        }
                )
            }
        }
    }
}

@Composable
fun WatchlistTvTab(
    tvShows: List<WatchlistTv>,
    navigateToTvDetail: (Int) -> Unit
) {
    if (tvShows.isEmpty()) {
        EmptyState()
    } else {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(135.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(12.dp),
            modifier = Modifier
        ) {
            items(tvShows) { tvShow ->
                WatchlistTvItem(
                    tvShow = tvShow,
                    modifier = Modifier
                        .clickable {
                            navigateToTvDetail(tvShow.id)
                        }
                )
            }
        }
    }
}