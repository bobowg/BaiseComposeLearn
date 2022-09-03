package com.example.baisecomposelearn.screens.media

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.screens.components.*

@Composable
fun ComposeParticleScreen(navController: NavController) {
    val num = (1..5).random()
    Surface {
        when (num) {
            1 -> Fountain()
            2 -> Confetti()
            3 -> Meteor()
            4 -> Explosion()
            5 ->  SnowFall()
        }

    }

}

@Preview
@Composable
fun ComposeParticleScreenPreview() {
    ComposeParticleScreen(navController = rememberNavController())
}