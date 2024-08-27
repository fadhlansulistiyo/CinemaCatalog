package com.fadhlansulistiyo.cinemacatalog.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.NOW_PLAYING
import com.fadhlansulistiyo.cinemacatalog.ui.components.NowPlayingList
import com.fadhlansulistiyo.cinemacatalog.ui.components.SectionText
import com.fadhlansulistiyo.cinemacatalog.ui.theme.CinemaCatalogTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToDetails: (Int) -> Unit = {}
) {
    val nowPlayingMovies = viewModel.nowPlayingMovies.collectAsLazyPagingItems()

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
                    navigateToDetails = navigateToDetails
                )
            }
        )
    }
}