package com.fadhlansulistiyo.cinemacatalog.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import com.fadhlansulistiyo.cinemacatalog.R
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.Cast
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.BIOGRAPHY
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.CAST
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.GENRES
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.OVERVIEW
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.PRODUCTION_COMPANIES

@Composable
fun DetailContent(
    backdropUrl: String,
    posterUrl: String,
    title: String,
    additionalInfo: @Composable () -> Unit,
    genres: String? = null,
    isCinema: Boolean = false,
    description: String,
    productionCompanies: String? = null,
    castList: List<Cast> = emptyList(),
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
            Box(
                modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = loadImagePainter(imageUrl = backdropUrl),
                    contentDescription = stringResource(R.string.movie_backdrop),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .matchParentSize()
                )

                // Dark overlay
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .background(Color.Black.copy(alpha = 0.5f))
                )

            }

            // Back Button
            IconButton(
                onClick = onBackClick,
                modifier = Modifier
                    .size(40.dp)
                    .padding(start = 12.dp, top = 12.dp)
                    .align(Alignment.TopStart)
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_arrow_back_24),
                    contentDescription = null,
                    tint = Color.White
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
                    contentDescription = null,
                    tint = Color.White
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
                Text(
                    text = title,
                    fontSize = 18.sp,
                    color = Color.White,
                    fontFamily = FontFamily(Font(R.font.urbanist_extrabold)),
                    minLines = 2,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                )

                additionalInfo()

                // genres
                genres?.let {
                    SectionTitle(
                        text = GENRES,
                        modifier = Modifier
                            .padding(start = 8.dp, top = 6.dp)
                    )
                    Text(
                        text = it,
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
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = (-50).dp)
        ) {
            val sec: String = if (isCinema) OVERVIEW else BIOGRAPHY
            SectionTitle(
                text = sec,
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp)
            )
            Text(
                text = description,
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 4.dp)
                    .fillMaxWidth()
            )

            productionCompanies?.let {
                SectionTitle(
                    text = PRODUCTION_COMPANIES,
                    modifier = Modifier
                        .padding(start = 16.dp, top = 8.dp)
                )
                Text(
                    text = it,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 4.dp)
                        .fillMaxWidth()
                )
            }

            // cast list
            if (castList.isNotEmpty()) {
                SectionTitle(
                    text = CAST,
                    modifier = Modifier
                        .padding(start = 16.dp, top = 8.dp)
                )
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(castList.size) { index ->
                        val cast = castList[index]
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
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        fontSize = 16.sp,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier
    )
}