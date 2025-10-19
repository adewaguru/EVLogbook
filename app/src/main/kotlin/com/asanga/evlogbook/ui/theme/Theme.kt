package com.asanga.evlogbook.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = Color(0xFF2563EB),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFDBE4FF),
    onPrimaryContainer = Color(0xFF001B3D),
    secondary = Color(0xFF16A34A),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFD1FAE5),
    onSecondaryContainer = Color(0xFF052E16),
    tertiary = Color(0xFFDC2626),
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFFFE4E6),
    onTertiaryContainer = Color(0xFF450A0A),
    error = Color(0xFFDC2626),
    onError = Color.White,
    errorContainer = Color(0xFFFFE4E6),
    onErrorContainer = Color(0xFF450A0A),
    background = Color(0xFFFAFAFA),
    onBackground = Color(0xFF1A1A1A),
    surface = Color.White,
    onSurface = Color(0xFF1A1A1A),
    surfaceVariant = Color(0xFFF5F5F5),
    onSurfaceVariant = Color(0xFF666666),
    outline = Color(0xFFD1D5DB),
    outlineVariant = Color(0xFFE5E7EB)
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFF60A5FA),
    onPrimary = Color(0xFF001B3D),
    primaryContainer = Color(0xFF1E40AF),
    onPrimaryContainer = Color(0xFFDBE4FF),
    secondary = Color(0xFF4ADE80),
    onSecondary = Color(0xFF052E16),
    secondaryContainer = Color(0xFF15803D),
    onSecondaryContainer = Color(0xFFD1FAE5),
    tertiary = Color(0xFFF87171),
    onTertiary = Color(0xFF450A0A),
    tertiaryContainer = Color(0xFFB91C1C),
    onTertiaryContainer = Color(0xFFFFE4E6),
    error = Color(0xFFF87171),
    onError = Color(0xFF450A0A),
    errorContainer = Color(0xFFB91C1C),
    onErrorContainer = Color(0xFFFFE4E6),
    background = Color(0xFF121212),
    onBackground = Color(0xFFE5E5E5),
    surface = Color(0xFF1E1E1E),
    onSurface = Color(0xFFE5E5E5),
    surfaceVariant = Color(0xFF2A2A2A),
    onSurfaceVariant = Color(0xFFB3B3B3),
    outline = Color(0xFF4A4A4A),
    outlineVariant = Color(0xFF3A3A3A)
)

@Composable
fun EVLogbookTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColors
        else -> LightColors
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
