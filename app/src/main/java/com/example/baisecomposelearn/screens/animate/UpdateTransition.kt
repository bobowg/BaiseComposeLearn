package com.example.baisecomposelearn.screens.animate

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.screens.components.ScreenModel


private enum class BoxState {
    Collapsed, Expanded
}

@Composable
fun UpdateTransition(navController: NavController) {
    var currentState by remember { mutableStateOf(BoxState.Expanded) }
    val transition = updateTransition(targetState = currentState, label = "")
    val color by transition.animateColor(label = "") { sate ->
        when (sate) {
            BoxState.Expanded -> Color.Red
            BoxState.Collapsed -> MaterialTheme.colors.primary
        }
    }
    val size by transition.animateDp(label = "") { state ->
        when (state) {
            BoxState.Collapsed -> 0.dp
            BoxState.Expanded -> 230.dp
        }
    }
    ScreenModel(navController = navController, content = {
        Button(
            onClick = {
                currentState = if (currentState == BoxState.Collapsed) {
                    BoxState.Expanded
                } else {
                    BoxState.Collapsed
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.updatetransition, "Rect动画"),
                fontSize = 24.sp
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .background(color = color)
                .size(size)
        )
    })

}

@Preview
@Composable
fun UpdateTransitionPreview() {
    UpdateTransition(navController = rememberNavController())
}