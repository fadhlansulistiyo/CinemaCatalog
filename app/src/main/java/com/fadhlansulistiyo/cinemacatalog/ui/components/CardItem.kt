package com.fadhlansulistiyo.cinemacatalog.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.fadhlansulistiyo.cinemacatalog.R

@Composable
fun MediaItem(
    modifier: Modifier = Modifier,
    imageUrl: String,
    title: String,
    showRating: Boolean = false,
    rating: String? = null,
) {
    Column(
        modifier = Modifier
            .width(135.dp)
            .wrapContentHeight()
    ) {
        Card(
            shape = MaterialTheme.shapes.medium,
            modifier = modifier
                .height(200.dp)
        ) {
            Image(
                painter = loadImagePainter(imageUrl = imageUrl),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxSize()
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = title,
            fontSize = 13.sp,
            style = MaterialTheme.typography.titleSmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .padding(start = 4.dp, bottom = if (showRating) 0.dp else 16.dp)
                .width(135.dp)
        )
        if (showRating && rating != null) {
            Spacer(modifier = Modifier.height(2.dp))
            Row(
                modifier = Modifier
                    .align(alignment = Alignment.Start)
                    .padding(start = 4.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_star_rate_12dp),
                    contentDescription = "Star Icon",
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.width(2.dp))
                Text(
                    text = rating,
                    fontSize = 13.sp,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                )
            }
        }
    }
}
