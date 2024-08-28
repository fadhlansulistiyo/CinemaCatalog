package com.fadhlansulistiyo.cinemacatalog.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.People
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.IMAGE_URL

@Composable
fun TrendingPeopleItem(
    people: People,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    MediaItem(
        imageUrl = "$IMAGE_URL${people.profilePath}",
        title = people.name,
        modifier = modifier,
        onClick = onClick
    )
}