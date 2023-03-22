package com.example.baisecomposelearn.screens.foundation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.screens.components.ScreenModel

@Composable
fun CacheDirScreen(navController: NavController) {
    ScreenModel(navController = navController, content = {

    })
}

@Preview
@Composable
fun CacheDirPreviewScreen() {
    CacheDirScreen(navController = rememberNavController())
}