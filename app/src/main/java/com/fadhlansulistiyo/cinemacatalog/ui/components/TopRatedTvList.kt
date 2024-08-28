package com.fadhlansulistiyo.cinemacatalog.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fadhlansulistiyo.cinemacatalog.core.data.Resource
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.Tv
import com.fadhlansulistiyo.cinemacatalog.ui.screen.home.HomeViewModel

@Composable
fun TopRatedTvList(
    modifier: Modifier = Modifier,
    topRatedTvState: Resource<List<Tv>>,
    navigateToDetails: (Int) -> Unit,
) {
    // val topRatedTvState by viewModel.topRatedTv.collectAsState()

    when (topRatedTvState) {
        is Resource.Loading -> {
            LoadingItem()
        }

        is Resource.Success -> {
            val tvShows = topRatedTvState.data

            // Check if tvShows is null or empty
            if (!tvShows.isNullOrEmpty()) {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(horizontal = 8.dp),
                    modifier = modifier
                ) {
                    items(tvShows) { tv ->
                        TopRatedTvItem(
                            tv = tv,
                            onClick = { navigateToDetails(tv.id) }
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
            (topRatedTvState as Resource.Error).message?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}
