package com.fadhlansulistiyo.cinemacatalog.ui.screen.detail.tvdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.fadhlansulistiyo.cinemacatalog.R
import com.fadhlansulistiyo.cinemacatalog.core.data.Resource
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.DetailTvWithCast
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.CAST
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.GENRES
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.IMAGE_URL_ORIGINAL
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.OVERVIEW
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.PRODUCTION_COMPANIES
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.UNKNOWN_ERROR
import com.fadhlansulistiyo.cinemacatalog.core.utils.toEpisodeString
import com.fadhlansulistiyo.cinemacatalog.core.utils.toFormattedDateString
import com.fadhlansulistiyo.cinemacatalog.core.utils.toSeasonString
import com.fadhlansulistiyo.cinemacatalog.core.utils.toVoteAverageFormat
import com.fadhlansulistiyo.cinemacatalog.ui.components.ErrorItem
import com.fadhlansulistiyo.cinemacatalog.ui.components.LoadingItem
import com.fadhlansulistiyo.cinemacatalog.ui.components.loadImagePainter
import com.fadhlansulistiyo.cinemacatalog.ui.screen.detail.moviedetail.CastList
import com.fadhlansulistiyo.cinemacatalog.ui.screen.detail.moviedetail.SectionTitle
import com.fadhlansulistiyo.cinemacatalog.ui.screen.detail.moviedetail.TextWithIcon

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
                onRetry = { viewModel.fetchTvDetails(tvId) }
            )
        }

        is Resource.Loading -> {
            LoadingItem()
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            val backdropUrl = "$IMAGE_URL_ORIGINAL${detailTv.detail.backdropPath}"

            Image(
                painter = loadImagePainter(imageUrl = backdropUrl),
                contentDescription = stringResource(R.string.movie_backdrop),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth()
            )

            // Back Button
            IconButton(
                onClick = {
                    onBackClick()
                },
                modifier = Modifier
                    .size(40.dp)
                    .padding(start = 12.dp, top = 12.dp)
                    .align(Alignment.TopStart)
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_arrow_back_24),
                    contentDescription = null
                )
            }

            // Watchlist Button
            IconButton(
                onClick = { /* Handle watchlist click */ },
                modifier = Modifier
                    .size(50.dp)
                    .padding(top = 12.dp, end = 12.dp)
                    .align(Alignment.TopEnd)
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_watchlist),
                    contentDescription = null
                )
            }

        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 16.dp)
                .offset(y = (-50).dp),
            verticalAlignment = Alignment.Top
        ) {
            val posterUrl = "$IMAGE_URL_ORIGINAL${detailTv.detail.posterPath}"

            // Poster
            Image(
                painter = loadImagePainter(imageUrl = posterUrl),
                contentDescription = stringResource(R.string.content_desc_poster),
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(width = 135.dp, height = 200.dp)
                    .clip(MaterialTheme.shapes.small)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .offset(y = (4).dp)
            ) {
                // Title
                Text(
                    text = detailTv.detail.name,
                    fontSize = 18.sp,
                    color = Color.White,
                    fontFamily = FontFamily(Font(R.font.urbanist_extrabold)),
                    minLines = 2,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                )

                Row {
                    // season
                    TextWithIcon(
                        text = detailTv.detail.numberOfSeasons.toSeasonString(),
                        icon = R.drawable.baseline_format_list_numbered_12,
                    )

                    // episode
                    TextWithIcon(
                        text = detailTv.detail.numberOfEpisodes.toEpisodeString(),
                        icon = R.drawable.baseline_circle_6,
                    )
                }

                // Release Date
                TextWithIcon(
                    text = detailTv.detail.firstAirDate.toFormattedDateString(),
                    icon = R.drawable.baseline_calendar_month_12,
                )

                // Vote Average
                TextWithIcon(
                    text = detailTv.detail.voteAverage.toVoteAverageFormat(1),
                    icon = R.drawable.ic_star_rate_12dp,
                    modifier = Modifier
                        .align(Alignment.Start)
                )

                // Genres
                SectionTitle(text = GENRES)
                Text(
                    text = detailTv.detail.genres.joinToString { it.name },
                    fontSize = 14.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleSmall,
                    fontFamily = FontFamily(Font(R.font.urbanist_medium)),
                    modifier = Modifier
                        .padding(start = 8.dp, top = 4.dp, end = 8.dp)
                        .fillMaxWidth()
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = (-50).dp)
        ) {
            // Overview
            SectionTitle(text = OVERVIEW)
            Text(
                text = detailTv.detail.overview,
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 4.dp)
                    .fillMaxWidth()
            )

            // Companies
            SectionTitle(text = PRODUCTION_COMPANIES)
            Text(
                text = detailTv.detail.productionCompanies.joinToString(
                    " • ",
                    prefix = " • "
                ) { it.name },
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 4.dp)
                    .fillMaxWidth()
            )

            // Cast Section
            SectionTitle(text = CAST)
            LazyRow(
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(detailTv.cast.size) { index ->
                    val cast = detailTv.cast[index]
                    CastList(
                        name = cast.name,
                        profilePath = cast.profilePath,
                        character = cast.character
                    )
                }
            }
        }
    }

}
