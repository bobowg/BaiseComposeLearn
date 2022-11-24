package com.example.baisecomposelearn.screens.animate


import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.lerp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.screens.components.ScreenModel
import kotlinx.coroutines.launch
import androidx.compose.runtime.*
import androidx.compose.ui.text.TextStyle

@Composable
fun AnimatingFonts(navController: NavController) {

    ScreenModel(navController = navController, content = {
        Animation()
        MaterialTheme()
    })

}

@Composable
private fun Animation() {
    val scope = rememberCoroutineScope()
    val animation = remember {
        Animatable(0f)
    }
    val h1 = MaterialTheme.typography.h1
    val h2 = MaterialTheme.typography.h2
    val textStyle by remember(animation.value) {
        derivedStateOf {
            lerp(h1, h2, animation.value)
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Animate me!", style = textStyle)
        Button(onClick = {
            scope.launch {
                animation.animateTo(1f, tween(500))
            }
        }) {
            Text(text = "Start")
        }
    }
}

@Composable
private fun MaterialTheme() {
    val h1 = MaterialTheme.typography.h1
    val h2 = MaterialTheme.typography.h2
    val h3 = MaterialTheme.typography.h3
    var textStyle by remember { mutableStateOf(h1) }
    val animatedTextStyle by animateTextStyleAsState(
        targetValue = textStyle,
        spring(stiffness = Spring.StiffnessLow)
    )
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Animate me!", style = animatedTextStyle)
        Button(onClick = {textStyle=h1}) {
            Text(text = "Animate to H1")
        }
        Button(onClick = {textStyle=h2}) {
            Text(text = "Animate to H2")
        }
        Button(onClick = {textStyle=h3}) {
            Text(text = "Animate to H3")
        }
    }
}

@Composable
private fun animateTextStyleAsState(
    targetValue: TextStyle,
    animationSpec: AnimationSpec<Float> = spring(),
    finishedListener: ((TextStyle) -> Unit)? = null
): State<TextStyle> {
    val animation = remember { Animatable(0f) }
    var previousTextStyle by remember { mutableStateOf(targetValue) }
    var nextTextStyle by remember { mutableStateOf(targetValue) }
    val textStyleState = remember(animation.value) {
        derivedStateOf { lerp(previousTextStyle, nextTextStyle, animation.value) }
    }
    LaunchedEffect(targetValue,animationSpec){
        previousTextStyle = textStyleState.value
        nextTextStyle = targetValue
        animation.snapTo(0f)
        animation.animateTo(1f,animationSpec)
        finishedListener?.invoke(textStyleState.value)
    }
    return textStyleState
}

@Preview
@Composable
fun AnimatingFontsPreview() {
    AnimatingFonts(navController = rememberNavController())
}