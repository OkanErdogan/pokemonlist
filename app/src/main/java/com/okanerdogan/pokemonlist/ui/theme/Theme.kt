package com.okanerdogan.pokemonlist.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkPalette = darkColors(
    primary = DarkGrntGreen,
    primaryVariant = DarkGrntBlue,
    secondary = DarkGrntBlack
)

private val LightPalette = lightColors(
    primary = LightGrntGreen,
    primaryVariant = LightGrntBlue,
    secondary = LightGrntWhite
)

@Composable
fun GRNTTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkPalette
    } else {
        LightPalette
    }


    MaterialTheme(
        colors = colors,
        content = content
    )
}