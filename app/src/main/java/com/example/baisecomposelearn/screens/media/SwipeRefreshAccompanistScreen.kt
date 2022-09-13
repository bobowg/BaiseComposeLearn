package com.example.baisecomposelearn.screens.media

import androidx.compose.runtime.*
import androidx.navigation.NavController

@Composable
fun SwipeRefreshAccompanistScreen(navController: NavController) {
    var refreshing by remember{ mutableStateOf(false)}
}