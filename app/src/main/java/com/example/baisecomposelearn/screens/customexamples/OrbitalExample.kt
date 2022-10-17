package com.example.baisecomposelearn.screens.customexamples

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.SpringSpec
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.screens.components.ScreenModel
import com.example.baisecomposelearn.screens.components.randomSampleImageUrl
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.orbital.Orbital
import com.skydoves.orbital.animateTransformation
import com.skydoves.orbital.rememberContentWithOrbitalScope

@Composable
fun OrbitalExampleScreen(navController: NavController) {
    ScreenModel(navController = navController, content = {
        FistEample()
        OrbitalExample()
    })
}

private val imageUrl = randomSampleImageUrl()

@Composable
private fun OrbitalExample() {
    val transformationSpec = SpringSpec<IntSize>(
        dampingRatio = Spring.DampingRatioMediumBouncy,
        stiffness = 200f
    )

    var isTransformed by rememberSaveable { mutableStateOf(false) }
    val poster = rememberContentWithOrbitalScope {
        GlideImage(
            modifier = if (isTransformed) {
                Modifier.size(300.dp, 620.dp)
            } else {
                Modifier.size(100.dp, 220.dp)
            }.animateTransformation(this, transformationSpec),
            imageModel = imageUrl,
            contentScale = ContentScale.Fit
        )
    }

    Orbital(
        modifier = Modifier
            .clickable { isTransformed = !isTransformed }
    ) {
        if (isTransformed) {
            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                poster()
            }
        } else {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Bottom
            ) {
                poster()
            }
        }

    }
}

@Composable
private fun FistEample() {
    val transformationSpec = SpringSpec<IntSize>(
        dampingRatio = Spring.DampingRatioMediumBouncy,
        stiffness = 200f
    )

    var isTransformed by rememberSaveable { mutableStateOf(false) }
    val poster = rememberContentWithOrbitalScope {
        GlideImage(
            modifier = if (isTransformed) {
                Modifier.size(300.dp, 620.dp)
            } else {
                Modifier.size(100.dp, 220.dp)
            }.animateTransformation(this, transformationSpec),
            imageModel = imageUrl,
            contentScale = ContentScale.Fit
        )
    }

    Orbital(
        modifier = Modifier
            .clickable { isTransformed = !isTransformed }
    ) {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            poster()
        }
    }
}
