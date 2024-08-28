package com.fadhlansulistiyo.cinemacatalog.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.fadhlansulistiyo.cinemacatalog.R
import com.fadhlansulistiyo.cinemacatalog.core.domain.model.Tv
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.IMAGE_URL
import com.fadhlansulistiyo.cinemacatalog.core.utils.toVoteAverageFormat

@Composable
fun TopRatedTvItem(
    tv: Tv,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val imageUrl = "$IMAGE_URL${tv.posterPath}"

    Column(
        modifier = modifier
            .width(135.dp)
            .wrapContentHeight()
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                model = imageUrl
            ),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(200.dp)
                .clip(MaterialTheme.shapes.medium)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = tv.name,
            fontSize = 13.sp,
            style = MaterialTheme.typography.titleSmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .padding(start = 4.dp)
                .width(135.dp)
        )
        Spacer(modifier = Modifier.height(2.dp))
        Row(
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .padding(start = 4.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_star_rate_12dp),
                contentDescription = "Star Icon",
                modifier = Modifier
            )
            Spacer(modifier = Modifier.width(2.dp))
            Text(
                text = tv.voteAverage.toVoteAverageFormat(1),
                fontSize = 13.sp,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
            )
        }
    }

}