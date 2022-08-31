package com.example.baisecomposelearn.screens.media

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.screens.components.*

@Composable
fun LineChartScreen(navController: NavController) {
    ScreenModel(navController = navController, content = {
        LineChart(times = times, color = colorGroup, lineChartData, chartTitle = "直流输入电压对比",legends = listOf("PV1"))
    })
}

@Preview
@Composable
fun LineChartScreenPreview() {
    LineChartScreen(navController = rememberNavController())
}