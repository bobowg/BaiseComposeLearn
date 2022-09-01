package com.example.baisecomposelearn.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import me.nikhilchaudhari.quarks.CreateParticles
import me.nikhilchaudhari.quarks.particle.*

@Composable
fun Fountain() {
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
            durationMillis = 10 * 1000,

            )
    }
}

@Composable
fun Confetti() {
    CreateParticles(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black),
        x = 500f, y = 200f,
        velocity = Velocity(xDirection = 2f, yDirection = -2f, randomize = true),
        force = Force.Gravity(0.3f),
        acceleration = Acceleration(),
        particleSize = ParticleSize.RandomSizes(20..60),
        particleColor = ParticleColor.RandomColors(
            listOf(
                Color.Yellow,
                Color.Blue,
                Color.Red,
                Color.White,
                Color.Magenta,
                Color.Green
            )
        ),
        lifeTime = LifeTime(255f, 2f),
        emissionType = EmissionType.FlowEmission(
            maxParticlesCount = EmissionType.FlowEmission.INDEFINITE,
            emissionRate = 0.8f
        ),
        durationMillis = 10 * 1000
    )
}

@Composable
fun Meteor() {
    CreateParticles(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black),
        x = 500f, y = 1200f,
        velocity = Velocity(xDirection = 1f, yDirection = 1f, randomize = true),
        force = Force.Wind(-0.2f, -0.1f),
        acceleration = Acceleration(-1f, -2f),
        particleSize = ParticleSize.ConstantSize(100f),
        particleColor = ParticleColor.SingleColor(Color.White),
        lifeTime = LifeTime(255f, 6f),
        emissionType = EmissionType.FlowEmission(
            maxParticlesCount = EmissionType.FlowEmission.INDEFINITE,
            emissionRate = 1f
        ),
        durationMillis = 10 * 1000
    )
}

@Composable
fun Explosion() {
    CreateParticles(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black),
        x = 500f, y = 1000f,
        velocity = Velocity(xDirection = -2f, yDirection = 2f),
        force = Force.Gravity(0.0f),
        acceleration = Acceleration(1f, 1f),
        particleSize = ParticleSize.RandomSizes(10..70),
        particleColor = ParticleColor.RandomColors(
            listOf(
                Color.Yellow,
                Color.Blue,
                Color.Red,
                Color.White,
                Color.Magenta,
                Color.Green
            )
        ),
        lifeTime = LifeTime(255f, 0.5f),
        emissionType = EmissionType.ExplodeEmission(numberOfParticles = 300),
        durationMillis = 10 * 1000
    )
}

@Composable
fun SnowFall() {
    CreateParticles(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black),
        x = 500f, y = -50f,
        velocity = Velocity(xDirection = 1f, yDirection = 1f, randomize = true),
        force = Force.Gravity(0.01f),
        acceleration = Acceleration(),
        particleSize = ParticleSize.RandomSizes(10..30),
        particleColor = ParticleColor.SingleColor(Color.White),
        lifeTime = LifeTime(255f, 0.01f),
        emissionType = EmissionType.FlowEmission(maxParticlesCount = 300, emissionRate = 0.5f),
        durationMillis = 10 * 1000
    )
}