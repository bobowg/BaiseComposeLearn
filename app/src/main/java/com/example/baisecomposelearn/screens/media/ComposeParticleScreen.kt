package com.example.baisecomposelearn.screens.media

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.screens.components.ScreenModel
import me.nikhilchaudhari.quarks.CreateParticles
import me.nikhilchaudhari.quarks.particle.*

@Composable
fun ComposeParticleScreen(navController: NavController) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black)
        ) {
            CreateParticles(
                modifier = Modifier.fillMaxSize(),
                x = 500f, y = 1000f,
                velocity = Velocity(xDirection = 1f, yDirection = 1f),
                force = Force.Gravity(0f),
                acceleration = Acceleration(0f, 0f),
                particleSize = ParticleSize.RandomSizes(25..100),
                particleColor = ParticleColor.RandomColors(
                    listOf(
                        Color.White,
                        Color.Gray,
                        Color.Red,
                        Color.Yellow,
                        Color.Blue
                    )
                ),
                lifeTime = LifeTime(255f, 0.2f),
                emissionType = EmissionType.ExplodeEmission(numberOfParticles = 100),
                durationMillis =  10  *  1000 ,

            )
        }
}

@Preview
@Composable
fun ComposeParticleScreenPreview() {
    ComposeParticleScreen(navController = rememberNavController())
}