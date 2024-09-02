package com.fadhlansulistiyo.cinemacatalog.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fadhlansulistiyo.cinemacatalog.R
import com.fadhlansulistiyo.cinemacatalog.core.utils.Constants.IMAGE_URL

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

@Composable
fun CastItem(
    modifier: Modifier = Modifier,
    profilePath: String,
    name: String,
    character: String,
    onClick: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .width(100.dp)
            .wrapContentHeight()
            .clickable(onClick = onClick)

    ) {
        Card(
            shape = MaterialTheme.shapes.medium,
            modifier = modifier
                .height(150.dp)
        ) {
            // profile path
            Image(
                painter = loadImagePainter(imageUrl = profilePath),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxSize()
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        // name
        Text(
            text = name,
            fontSize = 12.sp,
            style = MaterialTheme.typography.titleSmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(start = 4.dp)
        )
        // character
        Text(
            text = character,
            fontSize = 11.sp,
            maxLines = 1,
            style = MaterialTheme.typography.titleSmall,
            overflow = TextOverflow.Ellipsis,
            fontFamily = FontFamily(Font(R.font.urbanist_light)),
            modifier = Modifier
                .padding(start = 4.dp, top = 2.dp)
        )
    }
}
