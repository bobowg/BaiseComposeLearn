package com.example.baisecomposelearn.screens.foundation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.*
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.screens.components.goBack
import com.example.baisecomposelearn.screens.components.island.IslandBubbleContent
import com.example.baisecomposelearn.screens.components.island.IslandContent
import com.example.baisecomposelearn.screens.components.island.IslandState
import com.example.baisecomposelearn.screens.components.island.RadioButtonRow
import com.example.baisecomposelearn.screens.components.island.metaball.MetaContainer
import com.example.baisecomposelearn.screens.components.island.metaball.MetaEntity
import com.example.baisecomposelearn.theme.darkBackgroundColor
import kotlinx.coroutines.launch
import kotlin.math.roundToInt


@Composable
fun DynamicIslandCompose(navController: NavController) {
    Column {
        var islandState: IslandState by remember { mutableStateOf(IslandState.DefaultState()) }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            DynamicIsland(islandState = islandState)
            RadioButtonRow(text="Default",selected = islandState is IslandState.DefaultState){
                islandState = IslandState.DefaultState()
            }
            RadioButtonRow(text = "Call state", selected = islandState is IslandState.CallState) {
                islandState = IslandState.CallState()
            }
            RadioButtonRow(
                text = "Call Timer state",
                selected = islandState is IslandState.CallTimerState
            ) {
                islandState = IslandState.CallTimerState()
            }

            RadioButtonRow(
                text = "Face unlock state",
                selected = islandState is IslandState.FaceUnlockState
            ) {
                islandState = IslandState.FaceUnlockState()
            }
        }
       else{
          Text(text = stringResource(id =R.string.message_not_supported))
        }
        goBack(navController = navController)
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun DynamicIsland(islandState: IslandState) {
    val config = LocalConfiguration.current
    val startPadding by animateDpAsState(
        targetValue = (config.screenWidthDp.dp / 2) - islandState.fullWidth / 2,
        animationSpec = spring(
            stiffness = Spring.StiffnessLow,
            dampingRatio = Spring.DampingRatioHighBouncy
        )
    )
    val scope = rememberCoroutineScope()
    val shake = remember { Animatable(0f) }
    LaunchedEffect(islandState.hasBubbleContent) {
        scope.launch {
            shake.animateTo(15f)
            shake.animateTo(
                targetValue = 0f,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
        }
    }

    MetaContainer(modifier = Modifier.height(200.dp)) {
        Row(
            modifier = Modifier
                .padding(top = 20.dp)
                .padding(start = startPadding)
                .fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            MetaEntity(
                modifier = Modifier
                    .offset { IntOffset(shake.value.roundToInt(), 0) }
                    .zIndex(10f),
                metaContent = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = Color.Black, shape = RoundedCornerShape(35.dp))
                    )
                }
            ) {
                IslandContent(state = islandState)
            }
            AnimatedVisibility(
                visible = islandState.hasBubbleContent,
                modifier = Modifier.padding(start = 8.dp),
                enter = bubbleEnterTransition,
                exit = bubbleExitTransition
            ) {
                MetaEntity(
                    metaContent = {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    color = darkBackgroundColor,
                                    shape = RoundedCornerShape(50.dp)
                                )
                        )
                    }) {
                    IslandBubbleContent(state = islandState)
                }
            }

        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
private val bubbleEnterTransition = scaleIn(initialScale = 0.7f) + slideInHorizontally(
    animationSpec = spring(
        stiffness = Spring.StiffnessLow,
        dampingRatio = Spring.DampingRatioLowBouncy,
    )
) {
    -it
}

@OptIn(ExperimentalAnimationApi::class)
private val bubbleExitTransition = scaleOut(targetScale = .7f) + slideOutHorizontally(
    animationSpec = spring(
        stiffness = Spring.StiffnessLow
    )
) { (-it * 1.2f).roundToInt() }