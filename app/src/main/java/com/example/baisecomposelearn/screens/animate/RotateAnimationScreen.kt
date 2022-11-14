package com.example.baisecomposelearn.screens.animate

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.screens.components.ScreenModel

@Composable
fun RotateAnimationScreen(navController: NavController) {
    ScreenModel(navController = navController, content = {
        RoateAnimationExample()
    })
}

@Composable
private fun RoateAnimationExample() {
    val infiniteTransition = rememberInfiniteTransition()
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )
    Box(modifier = Modifier
        .padding(16.dp)
        .fillMaxSize()) {
        Canvas(modifier = Modifier
            .size(70.dp)
            .align(Alignment.TopCenter)) {
            rotate(rotation) {
                drawRect(Color.Red, size = size)
            }
        }
    }
}

@Preview
@Composable
fun RotateAnimationScreenPreview() {
    RotateAnimationScreen(navController = rememberNavController())
}