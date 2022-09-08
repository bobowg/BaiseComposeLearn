package com.example.baisecomposelearn.screens.room

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.screens.components.ScreenModel

@Composable
fun RoomDatabaseScreen(navController: NavController) {
    ScreenModel(navController = navController, content = {

    })
}

@Preview
@Composable
fun RoomDatabaseScreenPrview() {
    RoomDatabaseScreen(navController = rememberNavController())
}