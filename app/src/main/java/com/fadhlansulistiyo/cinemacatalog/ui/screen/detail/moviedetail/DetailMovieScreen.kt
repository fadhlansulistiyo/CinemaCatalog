package com.fadhlansulistiyo.cinemacatalog.ui.screen.detail.moviedetail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.fadhlansulistiyo.cinemacatalog.R
import com.fadhlansulistiyo.cinemacatalog.core.data.Resource
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.DetailMovieWithCast
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.IMAGE_URL_ORIGINAL
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.UNKNOWN_ERROR
import com.fadhlansulistiyo.cinemacatalog.core.utils.toFormattedDateString
import com.fadhlansulistiyo.cinemacatalog.core.utils.toFormattedRuntime
import com.fadhlansulistiyo.cinemacatalog.core.utils.toVoteAverageFormat
import com.fadhlansulistiyo.cinemacatalog.ui.components.DetailContent
import com.fadhlansulistiyo.cinemacatalog.ui.components.ErrorItem
import com.fadhlansulistiyo.cinemacatalog.ui.components.LoadingItem
import com.fadhlansulistiyo.cinemacatalog.ui.components.TextWithIcon

@Composable
fun DetailMovieScreen(
    movieId: Int,
    navigateBack: () -> Unit,
    viewModel: DetailMovieViewModel = hiltViewModel()
) {
    val movieDetails by viewModel.detailMovie.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchMovieDetails(movieId)
    }

    when (movieDetails) {
        is Resource.Error -> {
            ErrorItem(
                message = movieDetails?.message ?: UNKNOWN_ERROR,
                onRetry = { viewModel.fetchMovieDetails(movieId) },
                modifier = Modifier.fillMaxSize()
            )
        }

        is Resource.Loading -> {
            LoadingItem(
                modifier = Modifier.fillMaxSize()
            )
        }

        is Resource.Success -> {
            val detailMovie = (movieDetails as Resource.Success).data
            if (detailMovie != null) {
                DetailMovieContent(
                    detailMovie = detailMovie,
                    onBackClick = navigateBack
                )
            }
        }

        null -> {}
    }
}

@Composable
fun DetailMovieContent(
    detailMovie: DetailMovieWithCast,
    onBackClick: () -> Unit,
) {
    DetailContent(
        backdropUrl = "$IMAGE_URL_ORIGINAL${detailMovie.detail.backdropPath}",
        posterUrl = "$IMAGE_URL_ORIGINAL${detailMovie.detail.posterPath}",
        title = detailMovie.detail.title,
        additionalInfo = {
            TextWithIcon(
                text = detailMovie.detail.voteAverage.toVoteAverageFormat(1),
                icon = R.drawable.ic_star_rate_12dp,
            )

            TextWithIcon(
                text = detailMovie.detail.runtime.toFormattedRuntime(),
                icon = R.drawable.baseline_access_time_12,
            )

            TextWithIcon(
                text = detailMovie.detail.releaseDate.toFormattedDateString(),
                icon = R.drawable.baseline_calendar_month_12,
            )
        },
        genres = detailMovie.detail.genres.joinToString { it.name },
        isCinema = true,
        description = detailMovie.detail.overview,
        productionCompanies = detailMovie.detail.productionCompanies.joinToString(
            " • ",
            " • "
        ) { it.name },
        castList = detailMovie.cast,
        onBackClick = onBackClick
    )
}