package com.example.baisecomposelearn.screens.media

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.navitegation.NavitemScreen
import com.example.baisecomposelearn.screens.components.ScreenModel

@Composable
fun MediaextensionScreen(navController: NavController) {

    ScreenModel(navController = navController, content = {
        Button(
            onClick = { navController.navigate(NavitemScreen.Media3ExoplayerScreen.route) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = stringResource(id = R.string.media3exoplayer), fontSize = 24.sp)
        }
        Button(
            onClick = { navController.navigate(NavitemScreen.CoilImageScreen.route) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = stringResource(id = R.string.coilimage), fontSize = 24.sp)
        }
    })
}