package com.fadhlansulistiyo.cinemacatalog.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import coil.compose.rememberAsyncImagePainter
import com.fadhlansulistiyo.cinemacatalog.R

@Composable
fun loadImagePainter(
    imageUrl: String?,
    placeholder: Painter = painterResource(R.drawable.placeholder_image),
    error: Painter = painterResource(R.drawable.error_image)
): Painter {
    return rememberAsyncImagePainter(
        model = imageUrl,
        placeholder = placeholder,
        error = error
    )
}