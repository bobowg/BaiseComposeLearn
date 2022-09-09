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
            Text(text = stringResource(id = R.string.coilimage,1), fontSize = 24.sp)
        }
        Button(
            onClick = { navController.navigate(NavitemScreen.CoilLandscapistScreen.route) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = stringResource(id = R.string.coilimage,2), fontSize = 24.sp)
        }
        Button(
            onClick = { navController.navigate(NavitemScreen.QrcodeScreen.route) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = stringResource(id = R.string.qrcode,""), fontSize = 24.sp)
        }
        Button(
            onClick = { navController.navigate(NavitemScreen.DownFontScreen.route) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = stringResource(id = R.string.font,""), fontSize = 24.sp)
        }
        Button(
            onClick = { navController.navigate(NavitemScreen.LineChartScreen.route) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = stringResource(id = R.string.linechart,""), fontSize = 24.sp)
        }
        Button(
            onClick = { navController.navigate(NavitemScreen.ComposeParticleScreen.route) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = stringResource(id = R.string.composeparticlescreen,""), fontSize = 24.sp)
        }
        Button(
            onClick = { navController.navigate(NavitemScreen.DocumentScreen.route) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = stringResource(id = R.string.document,"文档1"), fontSize = 24.sp)
        }
        Button(
            onClick = { navController.navigate(NavitemScreen.MaterialRichTextScreen.route) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = stringResource(id = R.string.document, "文档2"), fontSize = 24.sp)
        }
        Button(
            onClick = { navController.navigate(NavitemScreen.FrescoLandscapistScreen.route) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = stringResource(id = R.string.frescolandscapist), fontSize = 24.sp)
        }
        Button(
            onClick = { navController.navigate(NavitemScreen.FontAwesomeScreen.route) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = stringResource(id = R.string.fontawesomescreen), fontSize = 24.sp)
        }

    }, isGoBack = false)
}