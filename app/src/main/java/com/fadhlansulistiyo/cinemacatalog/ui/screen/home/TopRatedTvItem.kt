package com.fadhlansulistiyo.cinemacatalog.ui.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.Tv
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.IMAGE_URL
import com.fadhlansulistiyo.cinemacatalog.core.utils.toVoteAverageFormat
import com.fadhlansulistiyo.cinemacatalog.ui.components.MediaItem

@Composable
fun TopRatedTvItem(
    tv: Tv,
    modifier: Modifier = Modifier,
) {
    MediaItem(
        imageUrl = "$IMAGE_URL${tv.posterPath}",
        title = tv.name,
        modifier = modifier,
        showRating = true,
        rating = tv.voteAverage.toVoteAverageFormat(1),
        posterWidth = 135.dp,
        posterHeight = 200.dp
    )
}