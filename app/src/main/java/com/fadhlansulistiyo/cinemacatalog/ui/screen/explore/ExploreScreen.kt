package com.fadhlansulistiyo.cinemacatalog.ui.screen.explore

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.fadhlansulistiyo.cinemacatalog.R
import com.fadhlansulistiyo.cinemacatalog.ui.components.EmptyState
import com.fadhlansulistiyo.cinemacatalog.ui.components.SearchBar

@Composable
fun ExploreScreen(
    modifier: Modifier = Modifier,
    viewModel: ExploreViewModel = hiltViewModel(),
    navigateToMovieDetail: (Int) -> Unit,
    navigateToTvDetail: (Int) -> Unit,
) {
    val searchResults = viewModel.searchResults.collectAsLazyPagingItems()
    val query = viewModel.query.collectAsState().value

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        SearchBar(
            query = query,
            onQueryChanged = viewModel::setSearchQuery
        )
        if (query.isEmpty()) {
            InitialState()
        } else {
            when {
                searchResults.loadState.refresh is LoadState.NotLoading && searchResults.itemCount == 0 -> {
                    EmptyState()
                }
                else -> {
                    ExploreList(
                        lazyPagingItems = searchResults,
                        navigateToMovieDetail = navigateToMovieDetail,
                        navigateToTvDetail = navigateToTvDetail
                    )
                }
            }
        }
    }
}

@Composable
fun InitialState(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.explore_movie),
            contentDescription = null,
            modifier = Modifier.size(128.dp)
        )
        Text(
            text = stringResource(R.string.explore_movies_message),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}