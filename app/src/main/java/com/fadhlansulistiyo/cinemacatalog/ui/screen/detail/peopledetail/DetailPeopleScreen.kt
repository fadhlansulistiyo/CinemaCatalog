package com.fadhlansulistiyo.cinemacatalog.ui.screen.detail.peopledetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fadhlansulistiyo.cinemacatalog.R
import com.fadhlansulistiyo.cinemacatalog.core.data.Resource
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.DetailPeopleWithCredits
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.BIRTHDAY
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.IMAGE_URL
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.IMAGE_URL_ORIGINAL
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.KNOWN_FOR
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.PLACE_OF_BIRTH
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.UNKNOWN_ERROR
import com.fadhlansulistiyo.cinemacatalog.core.utils.toFormattedDateString
import com.fadhlansulistiyo.cinemacatalog.core.utils.toVoteAverageFormat
import com.fadhlansulistiyo.cinemacatalog.ui.components.DetailContent
import com.fadhlansulistiyo.cinemacatalog.ui.components.ErrorItem
import com.fadhlansulistiyo.cinemacatalog.ui.components.LoadingItem
import com.fadhlansulistiyo.cinemacatalog.ui.components.MediaItem
import com.fadhlansulistiyo.cinemacatalog.ui.components.SectionTitle

@Composable
fun DetailPeopleScreen(
    peopleId: Int,
    navigateBack: () -> Unit,
    viewModel: DetailPeopleViewModel = hiltViewModel()
) {
    val peopleDetail by viewModel.detailPeople.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchDetailPeople(peopleId)
    }

    when (peopleDetail) {
        is Resource.Error -> {
            ErrorItem(
                message = peopleDetail?.message ?: UNKNOWN_ERROR,
                onRetry = { viewModel.fetchDetailPeople(peopleId) },
                modifier = Modifier.fillMaxSize()
            )
        }

        is Resource.Loading -> {
            LoadingItem(
                modifier = Modifier.fillMaxSize()
            )
        }

        is Resource.Success -> {
            val detailPeople = (peopleDetail as Resource.Success).data
            if (detailPeople != null) {
                DetailPeopleContent(
                    detailPeople = detailPeople,
                    onBackClick = navigateBack
                )
            }

        }

        null -> {}
    }
}

@Composable
fun DetailPeopleContent(
    detailPeople: DetailPeopleWithCredits,
    onBackClick: () -> Unit,
) {
    DetailContent(
        backdropUrl = "$IMAGE_URL${detailPeople.detail.profilePath}",
        posterUrl = "$IMAGE_URL_ORIGINAL${detailPeople.detail.profilePath}",
        title = detailPeople.detail.name,
        additionalInfo = {
            // known for
            SectionTitle(
                text = KNOWN_FOR,
                modifier = Modifier
                    .padding(start = 8.dp, top = 8.dp)
            )
            Text(
                text = detailPeople.detail.knownForDepartment,
                fontSize = 14.sp,
                style = MaterialTheme.typography.titleMedium,
                fontFamily = FontFamily(Font(R.font.urbanist)),
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .fillMaxWidth()
            )

            // birthday
            SectionTitle(
                text = BIRTHDAY,
                modifier = Modifier
                    .padding(start = 8.dp, top = 8.dp)
            )
            Text(
                text = detailPeople.detail.birthday.toFormattedDateString(),
                fontSize = 14.sp,
                style = MaterialTheme.typography.titleMedium,
                fontFamily = FontFamily(Font(R.font.urbanist)),
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .fillMaxWidth()
            )

            // place of birth
            SectionTitle(
                text = PLACE_OF_BIRTH,
                modifier = Modifier
                    .padding(start = 8.dp, top = 8.dp)
            )
            Text(
                text = detailPeople.detail.placeOfBirth,
                fontSize = 14.sp,
                style = MaterialTheme.typography.titleMedium,
                fontFamily = FontFamily(Font(R.font.urbanist)),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .fillMaxWidth()
            )
        },
        description = detailPeople.detail.biography,
        additionalInfo2 = {
            // Known for movies
            SectionTitle(
                text = KNOWN_FOR,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )
            LazyRow(
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val movieList = detailPeople.credits
                items(movieList.size, key = { index -> movieList[index].id }) { index ->
                    val item = movieList[index]
                    MediaItem(
                        imageUrl = "$IMAGE_URL${item.posterPath}",
                        title = item.title,
                        showRating = true,
                        rating = item.voteAverage.toVoteAverageFormat(1),
                        posterWidth = 135.dp,
                        posterHeight = 200.dp
                    )
                }
            }

        },
        onBackClick = onBackClick
    )
}