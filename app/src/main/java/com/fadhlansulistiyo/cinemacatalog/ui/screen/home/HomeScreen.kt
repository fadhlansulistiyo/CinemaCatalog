package com.fadhlansulistiyo.cinemacatalog.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.LazyPagingItems
import com.fadhlansulistiyo.cinemacatalog.ui.components.MovieListScreen

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToDetails: (Int) -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Now Playing",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        MovieListScreen(navigateToDetails = navigateToDetails)
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_7_PRO)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
