package com.example.baisecomposelearn.screens.components.island

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Call
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.baisecomposelearn.screens.components.times
import com.example.baisecomposelearn.theme.rwGreen

@Composable
fun IslandContent(
    state: IslandState
) {
    val width by animateDpAsState(
        targetValue = state.fullWidth,
        animationSpec = spring(
            stiffness = Spring.StiffnessLow,
            dampingRatio = 0.6f
        )
    )
    val height by animateDpAsState(
        targetValue = state.contentSize.height, animationSpec = spring(
            stiffness = Spring.StiffnessLow,
            dampingRatio = 0.6f
        )
    )

    Box(
        modifier = Modifier
            .width(width)
            .height(height)
    ) {
        AnimatedVisibility(
            visible = state.hasMainContent,
            enter = fadeIn(
                animationSpec = tween(300, 300)
            )
        ) {
            Box(modifier = Modifier.size(state.contentSize)) {
                when (state) {
                    is IslandState.FaceUnlockState -> {
                        FaceUnlock()
                    }
                    else -> {

                    }
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            LeadingContent(state)
            Box(modifier = Modifier.weight(1f))
            TrailingContent(state)
        }
    }
}

@Composable
fun TrailingContent(
    state: IslandState
) {
    AnimatedVisibility(
        modifier = Modifier.fillMaxHeight(),
        visible = state.hasTrailContent,
        enter = fadeIn(animationSpec = tween(300, 300))
    ) {
        Box(
            Modifier
                .width(state.trailingContentSize),
            contentAlignment = Alignment.Center,
        ) {
            when (state) {
                is IslandState.CallState -> {
                    CallWaveform()
                }

                else -> {}
            }
        }
    }
}

@Composable
fun LeadingContent(
    state: IslandState
) {
    AnimatedVisibility(
        visible = state.hasLeadingContent,
        modifier = Modifier.fillMaxHeight(),
        enter = fadeIn(animationSpec = tween(300, 300))
    ) {
        Box(
            modifier = Modifier.width(state.leadingContentSize),
            contentAlignment = Alignment.Center
        ) {
            when (state) {
                is IslandState.CallState -> {
                    Text(text = times[0], color = rwGreen)
                }
                is IslandState.CallTimerState -> {
                    Icon(
                        imageVector = Icons.Rounded.Call,
                        contentDescription = null,
                        tint = rwGreen
                    )
                }
                else -> {

                }
            }
        }
    }
}