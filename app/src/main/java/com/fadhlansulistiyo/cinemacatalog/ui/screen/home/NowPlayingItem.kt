package com.fadhlansulistiyo.cinemacatalog.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.Movie
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.IMAGE_URL_ORIGINAL

@Composable
fun NowPlayingItem(
    movie: Movie,
    modifier: Modifier = Modifier,
) {
    val imageUrl = "$IMAGE_URL_ORIGINAL${movie.backdropPath}"

    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
            .width(350.dp)
            .height(200.dp)
    ) {
        Box {
            Image(
                painter = rememberAsyncImagePainter(
                    model = imageUrl
                ),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(MaterialTheme.shapes.medium)
            )
            Text(
                text = movie.title,
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 16.dp, bottom = 16.dp)
            )
        }

    }
}