package com.fadhlansulistiyo.cinemacatalog.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.Movie

@Composable
fun NowPlayingList(
    modifier: Modifier = Modifier,
    nowPlayingMovies: LazyPagingItems<Movie>,
    navigateToDetails: (Int) -> Unit,
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 8.dp),
        modifier = modifier
    ) {
        items(nowPlayingMovies.itemCount) { index ->
            val movie = nowPlayingMovies[index]
            movie?.let {
                NowPlayingItem(
                    movie = it,
                    onClick = { navigateToDetails(movie.id) }
                )
            }
        }

        when {
            nowPlayingMovies.loadState.refresh is LoadState.Loading -> {
                item { LoadingItem() }
            }

            nowPlayingMovies.loadState.append is LoadState.Loading -> {
                item { LoadingItem() }
            }

            nowPlayingMovies.loadState.refresh is LoadState.Error -> {
                val error = nowPlayingMovies.loadState.refresh as LoadState.Error
                item {
                    ErrorItem(
                        message = error.error.localizedMessage ?: "Unknown error",
                        onRetry = { nowPlayingMovies.retry() }
                    )
                }
            }

            nowPlayingMovies.loadState.append is LoadState.Error -> {
                val error = nowPlayingMovies.loadState.append as LoadState.Error
                item {
                    ErrorItem(
                        message = error.error.localizedMessage ?: "Unknown error",
                        onRetry = { nowPlayingMovies.retry() }
                    )
                }
            }
        }
    }
}