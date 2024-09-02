package com.fadhlansulistiyo.cinemacatalog.ui.screen.watchlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun WatchlistScreen(
    modifier: Modifier = Modifier,
    viewModel: WatchlistViewModel = hiltViewModel(),
    navigateToMovieDetail: (Int) -> Unit,
    navigateToTvDetail: (Int) -> Unit,
) {
    val movieWatchlist by viewModel.movieWatchlist.collectAsState(initial = emptyList())
    val tvWatchlist by viewModel.tvWatchlist.collectAsState(initial = emptyList())

    val pagerState = rememberPagerState()
    val tabs = listOf("Movies", "TV Shows")

    Column {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        CoroutineScope(Dispatchers.Main).launch {
                            pagerState.scrollToPage(index)
                        }
                    },
                    text = { Text(text = title) }
                )
            }
        }
        HorizontalPager(
            count = tabs.size,
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) { page ->
            when (page) {
                0 -> WatchlistMovieTab(
                    movies = movieWatchlist,
                    navigateToMovieDetail = navigateToMovieDetail
                )
                1 -> WatchlistTvTab(
                    tvShows = tvWatchlist,
                    navigateToTvDetail = navigateToTvDetail
                )
            }
        }
    }
}