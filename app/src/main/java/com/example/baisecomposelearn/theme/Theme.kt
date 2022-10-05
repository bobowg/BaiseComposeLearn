package com.example.baisecomposelearn.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.android.haptics.samples.ui.theme.Shapes

private val LightThemeColors = lightColors(
    primary = rwGreen,
    primaryVariant = rwGreenDark,
    secondary = rwGreen
)

private val DarkThemeColors = darkColors(
    primary = Color(0xFF00A055),
    primaryVariant = Color(0xFF00F884),
    secondary = rwRed,
    onPrimary = Color.White,
)
private val DarkColorPalette = darkColors(
    primary = darkBackgroundColor,
    onPrimary = darkTextColorPrimary,
    primaryVariant = darkOverviewSurface,
    secondary = colorAccentPrimary,
    onSecondary = textColorPrimary,
    surface = darkBackgroundColor,
    onSurface = darkTextColorPrimary,
    background = darkBackgroundColor,
    onBackground = darkTextColorPrimary,
)

private val LightColorPalette = lightColors(
    primary = backgroundColor,
    onPrimary = textColorPrimary,
    primaryVariant = overviewSurface,
    secondary = colorAccentPrimary,
    onSecondary = textColorPrimary,
    surface = backgroundColor,
    onSurface = textColorPrimary,
    background = backgroundColor,
    onBackground = textColorPrimary,
)

val Colors.secondaryText: Color
    @Composable
    get() = if (isSystemInDarkTheme()) darkTextColorSecondary else textColorSecondary

val Colors.buttonSurface: Color
    @Composable
    get() = if (isSystemInDarkTheme()) darkSurfaceColor else surfaceColor

val Colors.buttonSurfaceDisabled: Color
    @Composable
    get() = if (isSystemInDarkTheme()) darkSurfaceColorVariant else surfaceColorVariant

val Colors.onButtonSurface: Color
    @Composable
    get() = if (isSystemInDarkTheme()) darkTextColorPrimary else textColorPrimary

val Colors.onButtonSurfaceDisabled: Color
    @Composable
    get() = if (isSystemInDarkTheme()) darkTextColorTertiary else textColorTertiary

val Colors.topAppBarBackgroundColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) darkSurfaceHeader else surfaceHeader

@Composable
fun HapticSamplerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    MaterialTheme(
        colors = if (darkTheme) DarkColorPalette else LightColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

/**
 * Responsible for switching color palette for dark and light theme.
 */
@Composable
fun BaiseComposeLearnTheme(content: @Composable () -> Unit) {
    val isDarkThemeEnabled = isSystemInDarkTheme() || BaiseComposeLearnThemeSettings.isDarkThemeEnabled
    val colors = if (isDarkThemeEnabled) DarkThemeColors else LightThemeColors

    MaterialTheme(colors = colors, content = content, typography = Typography)
}

/**
 * Allows changing between light and a dark theme from the app's settings.
 */
object BaiseComposeLearnThemeSettings {
    var isDarkThemeEnabled by mutableStateOf(false)
}