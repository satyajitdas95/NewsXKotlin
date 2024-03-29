package com.satyajit.newsappnew.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

const val stronglyDeemphasizedAlpha = 0.8f
const val midiumlyDeemphasizedAlpha = 0.5f
const val slightlyDeemphasizedAlpha = 0.87f

private val LightColorScheme = lightColorScheme(
    primary = Blue20,
    onPrimary = White00,
    inversePrimary = Orange20,
    primaryContainer = Blue10,
    onPrimaryContainer = White20,
    surface = Black40,
    onSurface = White05,
    onSurfaceVariant = White05
)

private val DarkColorScheme = darkColorScheme(
    primary = Blue20,
    onPrimary = White00,
    inversePrimary = Orange20,
    primaryContainer = Blue10,
    onPrimaryContainer = White20,
    surface = Black40,
    onSurface = White05,
    onSurfaceVariant = White05
)

@Composable
fun NewsAppNewTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.surface.toArgb()
            window.navigationBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}