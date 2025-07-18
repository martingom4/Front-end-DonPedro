package com.example.donpedro.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext


val LightColorScheme = lightColorScheme(
    primary      = PrimaryRed,
    onPrimary    = Color.White,
    secondary    = SecondarySalmon,
    onSecondary  = NeutralDark,
    tertiary     = TertiaryCream,
    onTertiary   = NeutralDark,
    background   = Color.White,
    onBackground = NeutralDark,
    surface      = Color.White,
    onSurface    = NeutralDark,
    surfaceVariant = NeutralGrey
)

val DarkColorScheme = darkColorScheme(
    primary      = SecondarySalmon,   // se invierte para resaltar sobre fondo oscuro
    onPrimary    = NeutralDark,
    secondary    = PrimaryRed,
    onSecondary  = Color.White,
    tertiary     = TertiaryCream,
    onTertiary   = NeutralDark,
    background   = NeutralDark,
    onBackground = Color.White,
    surface      = NeutralDark,
    onSurface    = Color.White,
    surfaceVariant = NeutralGrey
)

@Composable
fun DonPedroTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
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

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}