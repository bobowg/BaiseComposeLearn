package com.example.baisecomposelearn.screens.customexamples

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.screens.components.ScreenModel
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarConfig
import com.gowtham.ratingbar.RatingBarStyle
import timber.log.Timber

@Composable
fun RatingBarScreen(navController: NavController) {
    ScreenModel(navController = navController, content = {
        DefaultRatingBar()
        Spacer(modifier = Modifier.height(16.dp))
        HighlightedRatingBar()
    })
}

@Composable
private fun DefaultRatingBar() {
    var value: Float by rememberSaveable { mutableStateOf(3.5f) }
    RatingBar(
        value = value,
        onValueChange = { value = it },
        onRatingChanged = { Timber.d("Rating change to $it") })
}

@Composable
private fun HighlightedRatingBar() {
    var value: Float by rememberSaveable { mutableStateOf(3f) }
    RatingBar(
        config = RatingBarConfig().apply {
            numStars(6)
            size(32.dp)
            padding(8.dp)
            style(RatingBarStyle.HighLighted)
        },
        value = value,
        onValueChange = { value = it },
        onRatingChanged = { Timber.d("Rating change to $it") })
}

@Preview(showBackground = true)
@Composable
fun RatingBarScreenPreview() {
    RatingBarScreen(navController = rememberNavController())
}