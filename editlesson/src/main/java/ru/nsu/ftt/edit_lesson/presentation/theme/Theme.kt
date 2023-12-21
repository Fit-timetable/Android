package ru.nsu.ftt.edit_lesson.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorsScheme = lightColorScheme(
    surface = ScreenBackGround,
    primary = PrimaryColor,
    onPrimary = ScreenBackGround,
    secondary = SecondColor,
//    tertiary = OnPrimary,
    background = ScreenBackGround,
)

@Composable
fun FTTTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = LightColorsScheme
    rememberSystemUiController().apply {
        setSystemBarsColor(ScreenBackGround, darkIcons = true)
        setNavigationBarColor(ScreenBackGround, darkIcons = true)
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}