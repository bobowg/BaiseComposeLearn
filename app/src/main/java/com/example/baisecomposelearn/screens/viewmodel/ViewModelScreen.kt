package com.example.baisecomposelearn.screens.viewmodel

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.navitegation.NavitemScreen
import com.example.baisecomposelearn.screens.components.ScreenModel

@Composable
fun ViewModelScreen(navController: NavController) {
    ScreenModel(navController = navController,  content = {
        val modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
        Button(onClick = { navController.navigate(NavitemScreen.WellnessScreen.route) }, modifier = modifier) {
            Text(
                text = stringResource(id = R.string.wellnessglass,"多少"),
                fontSize = 24.sp
            )
        }
        Button(onClick = { navController.navigate(NavitemScreen.ViewModelFlowScreen.route) }, modifier = modifier) {
            Text(
                text = stringResource(id = R.string.flow),
                fontSize = 24.sp
            )
        }
        Button(onClick = { navController.navigate(NavitemScreen.ViewModelLiveDataScreen.route) }, modifier = modifier) {
            Text(
                text = stringResource(id = R.string.livedata),
                fontSize = 24.sp
            )
        }
    }, isGoBack = false)
}

@Preview
@Composable
fun ViewModelScreenPreview() {
    ViewModelScreen(navController = rememberNavController())
}
