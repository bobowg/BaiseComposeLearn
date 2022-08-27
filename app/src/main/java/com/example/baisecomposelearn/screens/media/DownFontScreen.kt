package com.example.baisecomposelearn.screens.media

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.screens.components.ScreenModel
import com.example.baisecomposelearn.theme.best
import com.example.baisecomposelearn.theme.kanit
import com.example.baisecomposelearn.theme.pacifico

@Composable
fun DownFontScreen(navController: NavController) {
    ScreenModel(navController = navController, content = {
        Text(
            text = stringResource(id = R.string.font,"text"),
            fontFamily = pacifico,
            color = Color.White,
            fontSize = 50.sp
        )
        Text(
            text = stringResource(id = R.string.font,"下载"),
            fontFamily = kanit,
            color = Color.White,
            fontSize = 50.sp
        )
        Text(
            text = stringResource(id = R.string.font,""),
            fontFamily = best,
            color = Color.White,
            fontSize = 50.sp
        )

    })
}

@Preview
@Composable
fun DownFontScreenPreview() {
    DownFontScreen(navController = rememberNavController())
}