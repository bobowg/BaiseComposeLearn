package com.example.baisecomposelearn.screens.media

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.screens.components.*
import com.example.baisecomposelearn.theme.best

@Composable
fun LineChartScreen(navController: NavController) {
    ScreenModel(navController = navController, content = {
        LineChart(times = times, color = colorGroup, lineChartData, chartTitle = "直流输入电压对比",legends = listOf("PV1"))
        Text(text = "线性图表",color= Color.White, fontFamily = best)
        BarChart()
        Text(text = "柱形图表",color= Color.White, fontFamily = best)
        PieChart()
        Text(text = "饼状图表",color= Color.White, fontFamily = best)
    })
}

@Preview
@Composable
fun LineChartScreenPreview() {
    LineChartScreen(navController = rememberNavController())
}