package com.fadhlansulistiyo.cinemacatalog.ui.screen.watchlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fadhlansulistiyo.cinemacatalog.R
import com.fadhlansulistiyo.cinemacatalog.ui.components.loadImagePainter

@Composable
fun WatchlistItem(
    modifier: Modifier = Modifier,
    title: String,
    imageUrl: String,
    rating: String
) {
    Column(
        modifier = modifier
            .wrapContentSize()
    ) {
        // Poster Image
        Card(
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier
                .aspectRatio(2f / 3f)
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Image(
                painter = loadImagePainter(imageUrl = imageUrl),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        // Title Text
        Text(
            text = title,
            fontSize = 13.sp,
            style = MaterialTheme.typography.titleSmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp)
        )
        Row(
            modifier = Modifier
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