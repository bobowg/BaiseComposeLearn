package com.example.baisecomposelearn.screens.animate

import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.screens.components.ScreenModel

@OptIn(ExperimentalAnimationGraphicsApi::class)
@Composable
fun AnimatedVectorDrawableScreen(navController: NavController) {
    val image = AnimatedImageVector.animatedVectorResource(R.drawable.ic_animation)
    var atEnd by remember { mutableStateOf(false) }
    ScreenModel(navController = navController, content = {
        Image(
            painter = rememberAnimatedVectorPainter(animatedImageVector = image, atEnd = atEnd),
            contentDescription = "Timer",
            modifier = Modifier.size(250.dp).clickable{
                atEnd =!atEnd
            },
            contentScale = ContentScale.Crop
        )
    })
}

@Preview
@Composable
fun AnimatedVectorDrawableScreenPreview() {
    AnimatedVectorDrawableScreen(navController = rememberNavController())
}