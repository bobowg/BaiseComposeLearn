package com.example.baisecomposelearn.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.example.baisecomposelearn.R


val best = FontFamily(Font(R.font.shouxieti))

val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
)

@OptIn(ExperimentalTextApi::class)
val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

@OptIn(ExperimentalTextApi::class)
val pacificoFontName = GoogleFont("Pacifico")

@OptIn(ExperimentalTextApi::class)
val pacifico = FontFamily(Font(googleFont = pacificoFontName, fontProvider = provider))

@OptIn(ExperimentalTextApi::class)
val kanitFontName = GoogleFont("Liu Jian Mao Cao")

@OptIn(ExperimentalTextApi::class)
val kanit = FontFamily(
    Font(googleFont = kanitFontName, fontProvider = provider, weight = FontWeight.Normal),
    Font(
        googleFont = kanitFontName,
        fontProvider = provider,
        weight = FontWeight.ExtraBold,
        style = FontStyle.Italic
    )
)
