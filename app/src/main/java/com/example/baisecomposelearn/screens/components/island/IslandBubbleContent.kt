package com.example.baisecomposelearn.screens.components.island

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.baisecomposelearn.theme.rwOrgen
import com.example.baisecomposelearn.R

@Composable
fun IslandBubbleContent(
    state: IslandState
) {
    val width = state.bubbleContentSize.width
    val height = state.bubbleContentSize.height

    val scale = remember { Animatable(1.5f) }
    LaunchedEffect(Unit) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = spring(
                stiffness = Spring.StiffnessLow,
                dampingRatio = 0.35f,
            )
        )
    }

    Box(
        modifier = Modifier
            .width(width * scale.value)
            .height(height),
        contentAlignment = Alignment.Center,
    ) {

        var bubbleContent: @Composable () -> Unit by remember {
            mutableStateOf({})
        }
        LaunchedEffect(state, block = {
            when (state) {
                is IslandState.CallTimerState -> {
                    bubbleContent = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_animation),
                            contentDescription = null,
                            tint = rwOrgen
                        )
                    }
                }

                else -> {}
            }
        })
        bubbleContent()
    }
}