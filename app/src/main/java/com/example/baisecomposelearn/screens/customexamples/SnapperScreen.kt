package com.example.baisecomposelearn.screens.customexamples

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.baisecomposelearn.screens.components.goBack
import com.example.baisecomposelearn.screens.components.randomSampleImageUrl
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior

@Composable
fun SnapperScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SnapperExample(navController = navController)
    }

}

@OptIn(ExperimentalSnapperApi::class)
@Composable
fun SnapperExample(navController: NavController) {
    val lazyListState = rememberLazyListState()
    val contentPadding = PaddingValues(16.dp)
    LazyColumn(
        state = lazyListState,
        flingBehavior = rememberSnapperFlingBehavior(
            lazyListState = lazyListState,
            endContentPadding = contentPadding.calculateBottomPadding(),
        ),
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(5) { index ->
            LazyItem(
                text = "${index + 1}",
                modifier = Modifier
                    .fillMaxWidth(),
            )
        }

    }
    goBack(navController = navController)
}

@Composable
fun LazyItem(text: String, modifier: Modifier) {
    Surface(modifier = modifier) {
        Box {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(randomSampleImageUrl(width = 400)).crossfade(true).build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Text(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
                    .background(MaterialTheme.colors.primary, CircleShape)
                    .sizeIn(minWidth = 40.dp, minHeight = 40.dp)
                    .padding(8.dp)
                    .wrapContentSize(Alignment.Center),
                text = text,
            )
        }

    }
}
