package com.example.baisecomposelearn.screens.media

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Image
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.appdrawer.DefaultTopAppBar
import com.example.baisecomposelearn.screens.components.randomSampleImageUrl
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun GlideLandscapistScreen(navController: NavController) {

    val listitem = listOf(
        randomSampleImageUrl(),
        randomSampleImageUrl(),
        randomSampleImageUrl(),
        randomSampleImageUrl(),
        randomSampleImageUrl(),
        randomSampleImageUrl(),
        randomSampleImageUrl(),
        randomSampleImageUrl(),
        randomSampleImageUrl(),
        randomSampleImageUrl(),
        randomSampleImageUrl(),
        randomSampleImageUrl(),
    )
    Scaffold(
        topBar = {
            DefaultTopAppBar(
                navController = navController,
                title = stringResource(id = R.string.glidelandscapist)
            )
        },
        content = { padding ->
            LazyVerticalGrid(columns = GridCells.Fixed(3)) {
                items(listitem) { item ->
                    GlideImage(
                        imageModel = item.let { it },
                        modifier = Modifier
                            .padding(padding)
                            .size(150.dp),
                        contentScale = ContentScale.Crop,
                        circularReveal = CircularReveal(duration = 300),
                        placeHolder = Icons.Filled.Image,
                        error = Icons.Filled.Error
                    )
                }
            }
        },
        contentColor = MaterialTheme.colors.primary
    )

}

