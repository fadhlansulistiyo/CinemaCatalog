package com.fadhlansulistiyo.cinemacatalog.ui.screen.watchlist

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.WatchlistTv
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.IMAGE_URL_ORIGINAL
import com.fadhlansulistiyo.cinemacatalog.core.utils.toVoteAverageFormat

@Composable
fun WatchlistTvItem(
    tvShow: WatchlistTv,
    modifier: Modifier = Modifier
) {
    WatchlistItem(
        title = tvShow.name,
        imageUrl = "$IMAGE_URL_ORIGINAL${tvShow.posterPath}",
        rating = tvShow.voteAverage.toVoteAverageFormat(1),
        modifier = modifier
    )
}