package com.fadhlansulistiyo.cinemacatalog.ui.components

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
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.DetailMovieWithCast
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.CAST
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.GENRES
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.IMAGE_URL
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.IMAGE_URL_ORIGINAL
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.OVERVIEW
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.PRODUCTION_COMPANIES
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.UNKNOWN_ERROR
import com.fadhlansulistiyo.cinemacatalog.core.utils.toFormattedDateString
import com.fadhlansulistiyo.cinemacatalog.core.utils.toFormattedRuntime
import com.fadhlansulistiyo.cinemacatalog.core.utils.toVoteAverageFormat
import com.fadhlansulistiyo.cinemacatalog.ui.screen.detail.moviedetail.DetailMovieViewModel

@Composable
fun DetailMovieScreen(
    movieId: Int,
    navigateBack: () -> Unit,
    viewModel: DetailMovieViewModel = hiltViewModel()
) {
    val movieDetails by viewModel.movieDetails.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchMovieDetails(movieId)
    }

    when (movieDetails) {
        is Resource.Error -> {
            ErrorItem(
                message = movieDetails?.message ?: UNKNOWN_ERROR,
                onRetry = { viewModel.fetchMovieDetails(movieId) }
            )
        }

        is Resource.Loading -> {
            LoadingItem()
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = "$IMAGE_URL_ORIGINAL${detailMovie.detail.backdropPath}"),
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
            // Poster
            Image(
                painter = rememberAsyncImagePainter(model = "$IMAGE_URL_ORIGINAL${detailMovie.detail.posterPath}"),
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
                    text = detailMovie.detail.title,
                    fontSize = 18.sp,
                    color = Color.White,
                    fontFamily = FontFamily(Font(R.font.urbanist_extrabold)),
                    minLines = 2,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                )

                // Vote Average
                TextWithIcon(
                    text = detailMovie.detail.voteAverage.toVoteAverageFormat(1),
                    icon = R.drawable.ic_star_rate_12dp,
                    modifier = Modifier
                        .align(Alignment.Start)
                )

                // Runtime
                TextWithIcon(
                    text = detailMovie.detail.runtime.toFormattedRuntime(),
                    icon = R.drawable.baseline_access_time_12,
                )

                // Release Date
                TextWithIcon(
                    text = detailMovie.detail.releaseDate.toFormattedDateString(),
                    icon = R.drawable.baseline_calendar_month_12,
                )

                // Genres
                SectionTitle(text = GENRES)
                Text(
                    text = detailMovie.detail.genres.joinToString { it.name },
                    fontSize = 14.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
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
            // Description
            SectionTitle(text = OVERVIEW)
            Text(
                text = detailMovie.detail.overview,
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 4.dp)
                    .fillMaxWidth()
            )

            // Companies
            SectionTitle(text = PRODUCTION_COMPANIES)
            Text(
                text = detailMovie.detail.productionCompanies.joinToString(
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
                items(detailMovie.cast.size) { index ->
                    val cast = detailMovie.cast[index]
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

@Composable
fun TextWithIcon(
    text: String, icon: Int, modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .wrapContentHeight()
            .padding(start = 8.dp, top = 4.dp)
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = text,
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.urbanist_medium))
        )
    }
}

@Composable
fun SectionTitle(
    text: String
) {
    Text(
        text = text,
        fontSize = 16.sp,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier
            .padding(start = 16.dp, top = 8.dp)
    )
}

@Composable
fun CastList(
    name: String,
    profilePath: String,
    character: String,
) {
    CastItem(
        profilePath = "$IMAGE_URL$profilePath",
        name = name,
        character = character,
        onClick = {},
        modifier = Modifier
    )
}
