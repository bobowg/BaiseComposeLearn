package com.example.baisecomposelearn.screens.animate

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.baisecomposelearn.screens.components.ScreenModel
import com.example.baisecomposelearn.theme.rwGreen

@Composable
fun ElevationAnimationScreen(navController: NavController) {
    val infiniteTransition = rememberInfiniteTransition()
    val elevation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 25f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )
    ScreenModel(navController = navController, content = {
        Box(
            modifier = Modifier
                .shadow(elevation.dp, RectangleShape, clip = false)
                .size(200.dp)
                .background(
                    rwGreen
                )
        )
    })

}