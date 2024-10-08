package com.fadhlansulistiyo.cinemacatalog.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.NOW_PLAYING
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.TOP_RATED_TV
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.TRENDING_PEOPLE

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToMovieDetail: (Int) -> Unit = {},
    navigateToTvDetail: (Int) -> Unit = {},
    navigateToPeopleDetail: (Int) -> Unit = {},

) {
    val nowPlayingMovies = viewModel.nowPlayingMovies.collectAsLazyPagingItems()
    val topRatedTvState by viewModel.topRatedTv.collectAsState()
    val trendingPeopleState by viewModel.trendingPeople.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        HomeSection(
            title = NOW_PLAYING,
            content = {
                NowPlayingList(
                    nowPlayingMovies = nowPlayingMovies,
                    navigateToMovieDetail = navigateToMovieDetail
                )
            }
        )
        HomeSection(
            title = TOP_RATED_TV,
            content = {
                TopRatedTvList(
                    topRatedTvState = topRatedTvState,
                    navigateToTvDetail = navigateToTvDetail,
                    onRetry = { viewModel.retryData() }
                )
            }
        )
        HomeSection(
            title = TRENDING_PEOPLE,
            content = {
                TrendingPeopleList(
                    trendingPeopleState = trendingPeopleState,
                    navigateToDetails = navigateToPeopleDetail,
                    onRetry = {viewModel.retryData()}
                )
            }
        )
    }
}