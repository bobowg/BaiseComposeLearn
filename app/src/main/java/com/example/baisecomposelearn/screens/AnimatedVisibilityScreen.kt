package com.example.baisecomposelearn.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.screens.components.ScreenModel

private enum class BoxSate {
    Small, Large
}

@Composable
fun AnimatedVisibilityScreen(navController: NavController) {
    var boxState by remember { mutableStateOf(BoxSate.Small) }

    val color = when (boxState) {
        BoxSate.Small -> Color.Blue
        BoxSate.Large -> Color.Yellow
    }
    val size = when (boxState) {
        BoxSate.Small -> 64.dp
        BoxSate.Large -> 128.dp
    }

    ScreenModel(navController = navController, content = {
        Button(
            onClick = {
                      boxState = when(boxState){
                          BoxSate.Large -> BoxSate.Small
                          BoxSate.Small -> BoxSate.Large
                      }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.animatedvisibility),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .size(size)
                .background(color)
        )
    })
}

@Preview
@Composable
fun AnimatedVisibilityScreenPreview() {
    AnimatedVisibilityScreen(navController = rememberNavController())
}