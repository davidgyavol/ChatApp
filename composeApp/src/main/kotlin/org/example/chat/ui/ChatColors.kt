package org.example.chat.ui

import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val primary = Color(0xFFE91E63)
val background = Color(0xFFF5F5F5)
val surfaceVariant = Color(0xFFEEEEEE)
val surface = Color(0xFFFFFFFF)
val onSurface = Color(0xFF212121)

val lightColorsScheme = lightColorScheme(
    primary = primary,
    onPrimary = surface,
    surface = surface,
    onSurface = onSurface,
    surfaceVariant = surfaceVariant,
    background = background
)