package com.example.baisecomposelearn.screens.animate

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AnimateColorComponent() {
    val currentColor by remember { mutableStateOf(Color.Red) }
    val transition = updateTransition(currentColor, label = "color")
    val color by transition.animateColor(
        transitionSpec = { TweenSpec<Color>(durationMillis = 2000) }, label = "animatecolor"
    ) { state ->
        when (state) {
            Color.Red -> Color.Green
            Color.Green -> Color.Blue
            Color.Blue -> Color.Red
            else -> Color.Red
        }
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = color)) { }

}

@Preview
@Composable
fun AnimateColorComponentPreview() {
    AnimateColorComponent()
}