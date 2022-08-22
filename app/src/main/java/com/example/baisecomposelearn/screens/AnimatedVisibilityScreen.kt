package com.example.baisecomposelearn.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.screens.components.ScreenModel

private enum class BoxSate {
    Small, Large
}

@Composable
fun AnimatedScreen(navController: NavController) {
    var boxState by remember { mutableStateOf(BoxSate.Small) }
    val transition = updateTransition(targetState = boxState, label = "")
    val color by transition.animateColor(label = "") { state ->
        when (state) {
            BoxSate.Small -> Color.Blue
            BoxSate.Large -> Color.Yellow
        }

    }
    var visble by remember{ mutableStateOf(true)}
    val size by transition.animateDp(transitionSpec = {
        if (targetState == BoxSate.Large) {
            tween(durationMillis = 1000, delayMillis = 50, easing = FastOutSlowInEasing)
        } else {
            tween(durationMillis = 1000, delayMillis = 50, easing = LinearOutSlowInEasing)
        }
    }, label = "") { state ->
        when (state) {
            BoxSate.Small -> 64.dp
            BoxSate.Large -> 128.dp
        }
    }

    ScreenModel(navController = navController, content = {
        Button(
            onClick = {
                boxState = when (boxState) {
                    BoxSate.Large -> BoxSate.Small
                    BoxSate.Small -> BoxSate.Large
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.animation1),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .size(size)
                .background(color)
        )

        Button(
            onClick = {
                visble = !visble
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
          
            Text(
                text = if (visble) stringResource(id = R.string.animatedvisibility,"显示") else stringResource(
                    id = R.string.animatedvisibility, "隐藏"
                ),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        AnimatedVisibility(visble) {
            Box(
                modifier = Modifier
                    .size(128.dp)
                    .background(Color.Gray)
            )
        }

    })
}

@Preview
@Composable
fun AnimatedScreenPreview() {
    AnimatedScreen(navController = rememberNavController())
}


