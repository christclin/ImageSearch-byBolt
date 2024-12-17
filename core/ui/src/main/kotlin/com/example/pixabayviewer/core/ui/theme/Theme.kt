package com.example.pixabayviewer.core.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = NordicBlue,
    secondary = NordicLightBlue,
    tertiary = NordicGreen,
    background = NordicWhite,
    surface = NordicWhite,
    error = NordicRed
)

@Composable
fun PixabayViewerTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        content = content
    )
}