package com.example.baisecomposelearn.screens.media

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.*
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.screens.components.ScreenModel

@Composable
fun LottieScreen(navController: NavController) {
    ScreenModel(navController = navController, content = {
        LottiePlayOnce()
        LottieRepeatForever()
    })
}

@Preview
@Composable
fun LottieScreenPreview() {
    LottieScreen(navController = rememberNavController())
}

@Composable
fun LottiePlayOnce() {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.heart_like))
    LottieAnimation(composition = composition, modifier = Modifier.size(150.dp))
    LottieUrlWithAnimate()
}

@Composable
fun LottieRepeatForever() {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.wave_loading))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever,
    )
    LottieAnimation(composition,progress, modifier = Modifier.size(150.dp))

}
private val annotaion = "https://assets2.lottiefiles.com/packages/lf20_1GkVPfS0cv.json"

@Composable
fun LottieUrlWithAnimate() {
    val anim = rememberLottieAnimatable()
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.Url(annotaion))
    LaunchedEffect(key1 = composition){
        anim.animate(composition, iterations = LottieConstants.IterateForever,)

    }
    LottieAnimation(
        composition = anim.composition,
        progress = anim.progress,
        modifier = Modifier.size(250.dp)
    )
}