package com.example.baisecomposelearn.screens.media

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import com.example.baisecomposelearn.R


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherScreen() {
}


@Composable
fun LoadingContent(
    modifier: Modifier = Modifier,
    text: String = ""
) {
    Text(text = text)
}

@Preview
@Composable
fun LoadingContentPreview() {
    LoadingContent()
}


@Composable
fun ErrorContent(
    modifier: Modifier = Modifier,
    onErrorClick: () -> kotlin.Unit
) {
    val compositon by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.heart_like))
    val progres by animateLottieCompositionAsState(
        composition = compositon,
        iterations = LottieConstants.IterateForever
    )
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.onSecondary),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(
            composition = compositon,
            progress = progres,
            modifier = Modifier.size(130.dp)
        )
        Button(onClick = { onErrorClick() }) {
            Text(text = stringResource(id = R.string.bad_network_view_tip))
        }
    }
}

@Preview
@Composable
fun ErrorContentPreview() {
    ErrorContent {}
}
