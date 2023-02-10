package com.example.baisecomposelearn.screens.foundation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.screens.components.ScreenModel

@Composable
fun BackgroundScreen(navController: NavController) {
    ScreenModel(navController = navController, content = {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(color = Color.Green)
            )
            Spacer(modifier = Modifier.height(15.dp))
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.Red, shape = RoundedCornerShape(12.dp))
            )
            Spacer(modifier = Modifier.height(15.dp))
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.Red, Color.Blue, Color.Green),
                            startY = 0.0f,
                            endY = 500.0f
                        ), shape = CutCornerShape(8.dp)
                    )
            )
        }
    })
}

@Preview
@Composable
fun BackgroundScreenPreview() {
    BackgroundScreen(navController = rememberNavController())
}