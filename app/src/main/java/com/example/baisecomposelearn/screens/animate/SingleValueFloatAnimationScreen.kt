package com.example.baisecomposelearn.screens.animate

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.baisecomposelearn.screens.components.goBack
import com.example.baisecomposelearn.theme.rwOrgen

@Composable
fun SingleValueFloatAnimationScreen(navController: NavController) {
    var enabled by remember { mutableStateOf(true) }
    val value by animateFloatAsState(
        targetValue = if (enabled) 0f else 100f,
        animationSpec = tween(500)
    )
    Box(modifier = Modifier
        .padding(value.dp)
        .clickable(onClick = { enabled = !enabled })
    ) {
       Box(modifier = Modifier
           .fillMaxSize()
           .background(rwOrgen))
    }
    goBack(navController = navController)
}