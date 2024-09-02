package com.fadhlansulistiyo.cinemacatalog.ui.screen.explore

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.MultiSearch
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.UNKNOWN_ERROR
import com.fadhlansulistiyo.cinemacatalog.ui.components.ErrorItem
import com.fadhlansulistiyo.cinemacatalog.ui.components.LoadingItem

@Composable
fun ExploreList(
    modifier: Modifier = Modifier,
    lazyPagingItems: LazyPagingItems<MultiSearch>,
    navigateToMovieDetail: (Int) -> Unit,
    navigateToTvDetail: (Int) -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(top = 4.dp),
        modifier = modifier
    ) {
        items(lazyPagingItems.itemCount) { index ->
            val movies = lazyPagingItems[index]
            movies?.let {
                ExploreItem(
                    posterPath = it.posterPath,
                    title = it.title,
                    airDate = it.releaseDate,
                    voteAverage = it.voteAverage,
                    overview = it.overview,
                    modifier = Modifier
                        .clickable {
                            if (it.mediaType == "movie") {
                                navigateToMovieDetail(it.id)
                            } else if (it.mediaType == "tv") {
                                navigateToTvDetail(it.id)
                            }
                        }
                )
            }
        }

        lazyPagingItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { LoadingItem(modifier = Modifier.fillParentMaxSize()) }
                }
                loadState.refresh is LoadState.Error -> {
                    val error = lazyPagingItems.loadState.refresh as LoadState.Error
                    item {
                        ErrorItem(
                            message = error.error.localizedMessage ?: UNKNOWN_ERROR,
                            onRetry = { lazyPagingItems.retry() },
                            modifier = Modifier.fillParentMaxSize()
                        )
                    }
                }
                loadState.append is LoadState.Error -> {
                    val error = lazyPagingItems.loadState.append as LoadState.Error
                    item {
                        ErrorItem(
                            message = error.error.localizedMessage ?: UNKNOWN_ERROR,
                            onRetry = { lazyPagingItems.retry() },
                            modifier = Modifier.fillParentMaxSize()
                        )
                    }
                }
            }
        }
    }
}