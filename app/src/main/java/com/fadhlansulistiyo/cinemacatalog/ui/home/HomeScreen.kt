package com.fadhlansulistiyo.cinemacatalog.ui.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    Text(text = "Home Screen")
}

@Preview(showBackground = true, device = Devices.PIXEL_7_PRO)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}