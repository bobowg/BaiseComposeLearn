package com.example.baisecomposelearn.screens.media

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.screens.components.SnowFall

@Composable
fun ComposeParticleScreen(navController: NavController) {
    SnowFall()
}

@Preview
@Composable
fun ComposeParticleScreenPreview() {
    ComposeParticleScreen(navController = rememberNavController())
}