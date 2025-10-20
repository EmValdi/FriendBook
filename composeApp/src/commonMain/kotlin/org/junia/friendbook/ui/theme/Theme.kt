package org.junia.friendbook.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = ButtonBlue,
    secondary = Grey,
    tertiary = TextBlue,
    background = White,
    onPrimary = White,
    onBackground = Black,
    error = Red
)

private val LightColorScheme = lightColorScheme(
    primary = ButtonBlue,
    secondary = Grey,
    tertiary = TextBlue,
    background = White,
    onPrimary = White,
    onBackground = Black,
    error = Red
)

// 🌗 Tema multiplataforma (sin dynamic color ni APIs Android)
@Composable
fun FriendbookTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}