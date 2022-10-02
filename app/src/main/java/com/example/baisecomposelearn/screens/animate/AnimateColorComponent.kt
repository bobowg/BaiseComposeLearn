package com.example.baisecomposelearn.screens.animate

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

enum class ColorSate {
    Black,
    Red,
    Green,
    Blue;
}

@Composable
fun AnimateColorComponent() {
    var state by remember { mutableStateOf(ColorSate.values().random()) }
    val color = when(state){
        ColorSate.Blue -> Color.Blue
        ColorSate.Red -> Color.Red
        ColorSate.Green -> Color.Green
        ColorSate.Black -> Color.Black
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                state = ColorSate
                    .values()
                    .random()
            }
            .background(color = color)
    ) {

    }

}

@Preview
@Composable
fun AnimateColorComponentPreview() {
    AnimateColorComponent()
}