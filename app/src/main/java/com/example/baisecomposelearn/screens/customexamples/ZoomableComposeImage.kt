package com.example.baisecomposelearn.screens.customexamples

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.screens.components.randomSampleImageUrl
import me.rerere.zoomableimage.ZoomableImage

@Composable
fun ZoomableComposeImageExample() {

    ZoomableImage(
        modifier = Modifier
            .fillMaxSize()
            .padding(6.dp),
        painter = rememberAsyncImagePainter(model = randomSampleImageUrl()),
        contentDescription = stringResource(id = R.string.zoomablecomposeimage)
    )
}
