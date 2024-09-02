package com.fadhlansulistiyo.cinemacatalog.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fadhlansulistiyo.cinemacatalog.core.data.Resource
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.Tv
import com.fadhlansulistiyo.cinemacatalog.ui.components.ErrorItem
import com.fadhlansulistiyo.cinemacatalog.ui.components.LoadingItem

@Composable
fun TopRatedTvList(
    modifier: Modifier = Modifier,
    topRatedTvState: Resource<List<Tv>>,
    navigateToTvDetail: (Int) -> Unit,
    onRetry: () -> Unit
) {
    when (topRatedTvState) {
        is Resource.Loading -> {
            LoadingItem()
        }

        is Resource.Success -> {
            val tvShows = topRatedTvState.data

            if (!tvShows.isNullOrEmpty()) {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(horizontal = 8.dp),
                    modifier = modifier
                ) {
                    items(tvShows) { tv ->
                        TopRatedTvItem(
                            tv = tv,
                            modifier = Modifier
                                .clickable { navigateToTvDetail(tv.id) }
                        )
                    }
                }
            } else {
                Text(
                    text = "No top-rated TV shows available",
                )
            }
        }

        is Resource.Error -> {
            topRatedTvState.message?.let {
                ErrorItem(
                    message = it,
                    onRetry = onRetry,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

    }
}
