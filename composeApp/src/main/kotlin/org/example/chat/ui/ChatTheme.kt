package org.example.chat.ui

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
fun ChatTheme(
    content: @Composable () -> Unit
) {
    val view = LocalView.current

    SideEffect {
        (view.context as? Activity)?.window?.let { window ->
            WindowCompat.getInsetsController(window, view).apply {
                isAppearanceLightStatusBars = true
            }
        }
    }

    MaterialTheme(
        colorScheme = lightColorsScheme,
        content = content
    )
}