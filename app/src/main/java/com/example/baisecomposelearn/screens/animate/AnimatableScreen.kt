package com.example.baisecomposelearn.screens.animate

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.screens.components.ScreenModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.screens.components.goBack
import kotlin.math.roundToInt

@Composable
fun AnimatableScreen(navController: NavController) {
    val animatedOffset = remember { Animatable(Offset(0f, 0f), Offset.VectorConverter) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .pointerInput(Unit) {
                coroutineScope {
                    while (true) {
                        val offset = awaitPointerEventScope {
                            awaitFirstDown().position
                        }
                        launch {
                            animatedOffset.animateTo(
                                offset,
                                animationSpec = spring(stiffness = Spring.StiffnessLow)
                            )
                        }
                    }
                }
            }
    ) {
        Text(
            stringResource(id = R.string.animatablescreen),
            modifier = Modifier.align(Alignment.Center)
        )
        Box(
            Modifier
                .offset {
                    IntOffset(
                        animatedOffset.value.x.roundToInt(),
                        animatedOffset.value.y.roundToInt()
                    )
                }
                .size(40.dp)
                .background(MaterialTheme.colors.primary, CircleShape)
        )
    }
    goBack(
        navController = navController,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 650.dp, bottom = 50.dp)
    )
}

@Preview
@Composable
fun AnimatableScreenPreview() {
    AnimatableScreen(navController = rememberNavController())
}