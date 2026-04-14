package com.example.cowboyz.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

// 🔵 Light Theme
private val LightColors = lightColorScheme(
    primary = RedPrimary,
    secondary = BluePrimary,
    tertiary = Accent,

    background = White,
    surface = White,

    onPrimary = White,
    onSecondary = White,
    onTertiary = White,

    onBackground = Black,
    onSurface = Black
)

// 🌙 Dark Theme
private val DarkColors = darkColorScheme(
    primary = RedPrimary,
    secondary = BluePrimary,
    tertiary = Accent,

    background = Black,
    surface = BlueDark,

    onPrimary = White,
    onSecondary = White,
    onTertiary = White,

    onBackground = White,
    onSurface = White
)

@Composable
fun CowBoyzTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = Typography,
        content = content
    )
}