package com.example.healthmonitorapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Define the color scheme
private val LightColors = lightColorScheme(
    primary = Color(0xFF4CAF50), // Green
    secondary = Color(0xFFFFC107), // Accent Yellow
    background = Color(0xFFE8F5E9), // Light Green Background
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black
)

// Apply the theme
@Composable
fun HealthMonitorAppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = Typography,
        content = content
    )
}
