package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private val AbilletLightColorScheme = lightColorScheme(
    primary = AbilletNavy,
    onPrimary = Color.White,
    primaryContainer = AbilletNavyContainer,
    onPrimaryContainer = AbilletNavy,
    secondary = AbilletAmber,
    onSecondary = AbilletNavyDark,
    secondaryContainer = AbilletAmberContainer,
    onSecondaryContainer = AbilletAmberDark,
    tertiary = EmeraldSuccess,
    onTertiary = Color.White,
    tertiaryContainer = EmeraldContainer,
    onTertiaryContainer = EmeraldSuccess,
    background = AbilletSlateBg,
    onBackground = AbilletTextPrimary,
    surface = AbilletSurface,
    onSurface = AbilletTextPrimary,
    surfaceVariant = AbilletSurfaceVariant,
    onSurfaceVariant = AbilletTextSecondary,
    outline = AbilletBorder,
    outlineVariant = AbilletBorder
)

private val AbilletDarkColorScheme = darkColorScheme(
    primary = AbilletDarkPrimary,
    onPrimary = AbilletNavyDark,
    primaryContainer = AbilletNavy,
    onPrimaryContainer = Color.White,
    secondary = AbilletAmber,
    onSecondary = AbilletNavyDark,
    secondaryContainer = AbilletAmberDark,
    onSecondaryContainer = Color.White,
    tertiary = SeatSelected,
    onTertiary = AbilletNavyDark,
    background = AbilletDarkBg,
    onBackground = Color(0xFFF1F5F9),
    surface = AbilletDarkSurface,
    onSurface = Color(0xFFF1F5F9),
    surfaceVariant = AbilletDarkSurfaceVariant,
    onSurfaceVariant = Color(0xFFCBD5E1),
    outline = Color(0xFF334155),
    outlineVariant = Color(0xFF1E293B)
)

val AbilletShapes = Shapes(
    extraSmall = RoundedCornerShape(8.dp),
    small = RoundedCornerShape(10.dp),
    medium = RoundedCornerShape(12.dp),
    large = RoundedCornerShape(16.dp),
    extraLarge = RoundedCornerShape(24.dp)
)

@Composable
fun AbilletTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) AbilletDarkColorScheme else AbilletLightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AbilletTypography,
        shapes = AbilletShapes,
        content = content
    )
}
