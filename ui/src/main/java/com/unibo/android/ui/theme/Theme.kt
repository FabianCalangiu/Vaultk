package com.unibo.android.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val VaultkColors = darkColorScheme(
    primary = Accent,
    background = Background,
    surface = Surface,
    onBackground = TextPrimary,
    onSurface = TextPrimary,
    error = Error
)

@Composable
fun VaultkTheme(
    content: @Composable () -> Unit
) {

    MaterialTheme(
        colorScheme = VaultkColors,
        typography = Typography,
        content = content
    )
}