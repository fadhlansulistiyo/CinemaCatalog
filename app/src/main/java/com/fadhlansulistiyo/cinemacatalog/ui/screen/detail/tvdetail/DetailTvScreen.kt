package com.fadhlansulistiyo.cinemacatalog.ui.screen.detail.tvdetail

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.fadhlansulistiyo.cinemacatalog.R
import com.fadhlansulistiyo.cinemacatalog.core.data.Resource
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.DetailTvWithCast
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.IMAGE_URL_ORIGINAL
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.UNKNOWN_ERROR
import com.fadhlansulistiyo.cinemacatalog.core.utils.toEpisodeString
import com.fadhlansulistiyo.cinemacatalog.core.utils.toFormattedDateString
import com.fadhlansulistiyo.cinemacatalog.core.utils.toSeasonString
import com.fadhlansulistiyo.cinemacatalog.core.utils.toVoteAverageFormat
import com.fadhlansulistiyo.cinemacatalog.ui.components.DetailContent
import com.fadhlansulistiyo.cinemacatalog.ui.components.ErrorItem
import com.fadhlansulistiyo.cinemacatalog.ui.components.LoadingItem
import com.fadhlansulistiyo.cinemacatalog.ui.components.TextWithIcon

@Composable
fun DetailTvScreen(
    tvId: Int,
    navigateBack: () -> Unit,
    viewModel: DetailTvViewModel = hiltViewModel()
) {
    val tvDetails by viewModel.tvDetails.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchTvDetails(tvId)
    }

    when (tvDetails) {
        is Resource.Error -> {
            ErrorItem(
                message = tvDetails?.message ?: UNKNOWN_ERROR,
                onRetry = { viewModel.fetchTvDetails(tvId) },
                modifier = Modifier.fillMaxSize()
            )
        }

        is Resource.Loading -> {
            LoadingItem(
                modifier = Modifier.fillMaxSize()
            )
        }

        is Resource.Success -> {
            val detailTv = (tvDetails as Resource.Success).data
            if (detailTv != null) {
                DetailTvContent(
                    detailTv = detailTv,
                    onBackClick = navigateBack
                )
            }
        }

        null -> {}
    }
}

@Composable
fun DetailTvContent(
    detailTv: DetailTvWithCast,
    onBackClick: () -> Unit,
) {
    DetailContent(
        backdropUrl = "$IMAGE_URL_ORIGINAL${detailTv.detail.backdropPath}",
        posterUrl = "$IMAGE_URL_ORIGINAL${detailTv.detail.posterPath}",
        title = detailTv.detail.name,
        additionalInfo = {
            Row {
                TextWithIcon(
                    text = detailTv.detail.numberOfSeasons.toSeasonString(),
                    icon = R.drawable.baseline_format_list_numbered_12,
                )

                TextWithIcon(
                    text = detailTv.detail.numberOfEpisodes.toEpisodeString(),
                    icon = R.drawable.baseline_circle_6,
                )
            }

            TextWithIcon(
                text = detailTv.detail.firstAirDate.toFormattedDateString(),
                icon = R.drawable.baseline_calendar_month_12,
            )

            TextWithIcon(
                text = detailTv.detail.voteAverage.toVoteAverageFormat(1),
                icon = R.drawable.ic_star_rate_12dp,
            )
        },
        genres = detailTv.detail.genres.joinToString { it.name },
        overview = detailTv.detail.overview,
        productionCompanies = detailTv.detail.productionCompanies.joinToString(" • ") { it.name },
        castList = detailTv.cast,
        onBackClick = onBackClick
    )
}