package com.fadhlansulistiyo.cinemacatalog.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.NOW_PLAYING
import com.fadhlansulistiyo.cinemacatalog.ui.components.NowPlayingList
import com.fadhlansulistiyo.cinemacatalog.ui.components.SectionText

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToDetails: (Int) -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        SectionText(NOW_PLAYING)
        NowPlayingList(navigateToDetails = navigateToDetails)
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_7_PRO)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
