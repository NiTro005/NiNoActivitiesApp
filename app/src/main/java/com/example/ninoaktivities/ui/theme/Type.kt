package com.example.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.ninoaktivities.R


val Montserrat = FontFamily(
    Font(R.font.montserrat_bold, weight = FontWeight.Bold),
    Font(R.font.montserrat_regular),
    Font(R.font.montserrat_thin_italic, weight = FontWeight.Thin, style = FontStyle.Italic)
)

val Roboto = FontFamily(
    Font(R.font.roboto_regular),
    Font(R.font.roboto_light, FontWeight.Light)
)

// Default Material 3 typography values

val AppTypography = Typography(
    displayMedium = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Bold
    ),
    displaySmall = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Normal
    ),
    headlineMedium = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Normal
    ),
    titleLarge = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Normal
    ),
    titleSmall = TextStyle(
        fontFamily = Montserrat,
        fontStyle = FontStyle.Italic,
        fontWeight = FontWeight.Thin
    ),
    bodyLarge = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Normal
    ),
    labelMedium = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Light
    ),
)

