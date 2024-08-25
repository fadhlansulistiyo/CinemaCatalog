package com.fadhlansulistiyo.cinemacatalog.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

val LightColorScheme = lightColorScheme(
    primary = md_theme_primary_light,
    onPrimary = md_theme_onPrimary_light,
    primaryContainer = md_theme_primaryContainer_light,
    onPrimaryContainer = md_theme_onPrimaryContainer_light,
    secondary = md_theme_secondary_light,
    onSecondary = md_theme_onSecondary_light,
    secondaryContainer = md_theme_secondaryContainer_light,
    onSecondaryContainer = md_theme_onSecondaryContainer_light,
    tertiary = md_theme_tertiary_light,
    onTertiary = md_theme_onTertiary_light,
    tertiaryContainer = md_theme_tertiaryContainer_light,
    onTertiaryContainer = md_theme_onTertiaryContainer_light
)

val DarkColorScheme = darkColorScheme(
    primary = md_theme_primary_dark,
    onPrimary = md_theme_onPrimary_dark,
    primaryContainer = md_theme_primaryContainer_dark,
    onPrimaryContainer = md_theme_onPrimaryContainer_dark,
    secondary = md_theme_secondary_dark,
    onSecondary = md_theme_onSecondary_dark,
    secondaryContainer = md_theme_secondaryContainer_dark,
    onSecondaryContainer = md_theme_onSecondaryContainer_dark,
    tertiary = md_theme_tertiary_dark,
    onTertiary = md_theme_onTertiary_dark,
    tertiaryContainer = md_theme_tertiaryContainer_dark,
    onTertiaryContainer = md_theme_onTertiaryContainer_dark
)

@Composable
fun CinemaCatalogTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}