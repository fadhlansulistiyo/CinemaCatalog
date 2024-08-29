package com.fadhlansulistiyo.cinemacatalog.ui.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.People
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.IMAGE_URL
import com.fadhlansulistiyo.cinemacatalog.ui.components.MediaItem

@Composable
fun TrendingPeopleItem(
    people: People,
    modifier: Modifier = Modifier,
) {
    MediaItem(
        imageUrl = "$IMAGE_URL${people.profilePath}",
        title = people.name,
        modifier = modifier,
    )
}