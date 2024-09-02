package com.fadhlansulistiyo.cinemacatalog.ui.screen.watchlist

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.WatchlistMovie
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.IMAGE_URL_ORIGINAL
import com.fadhlansulistiyo.cinemacatalog.core.utils.toVoteAverageFormat

@Composable
fun WatchlistMovieItem(
    movies: WatchlistMovie,
    modifier: Modifier = Modifier
) {
    WatchlistItem(
        title = movies.title,
        imageUrl = "$IMAGE_URL_ORIGINAL${movies.posterPath}",
        rating = movies.voteAverage.toVoteAverageFormat(1),
        modifier = modifier
    )
}