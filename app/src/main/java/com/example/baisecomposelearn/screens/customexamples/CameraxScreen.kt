package com.example.baisecomposelearn.screens.customexamples

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.baisecomposelearn.screens.components.camerax.VideoCaptureScreen

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun CameraxScreen(navController: NavController) {
    VideoCaptureScreen(navController)
}