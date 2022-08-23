package com.example.baisecomposelearn.screens.animate

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.screens.components.ScreenModel

@Composable
fun AnimationSpecScreen(navController: NavController) {
    var enabled by remember { mutableStateOf(true) }
    val alpha: Float by animateFloatAsState(
        targetValue = if (enabled) 1f else 0.5f,
//        animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioHighBouncy,
            stiffness = Spring.StiffnessVeryLow
        )
    )
    ScreenModel(navController = navController, content = {
        Button(
            onClick = {
                enabled = !enabled
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = stringResource(id = R.string.animationspec), fontSize = 24.sp)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .size(250.dp)
                .graphicsLayer(scaleX = alpha, scaleY = alpha)
                .background(
                    Color.Cyan
                )
        )
    })
}

@Preview
@Composable
fun AnimationSpecScreenPreview() {
    AnimationSpecScreen(navController = rememberNavController())
}