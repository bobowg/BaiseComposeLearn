package com.example.baisecomposelearn.screens.customexamples

import android.content.Intent
import android.os.Bundle
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.VibrationActivity
import com.example.baisecomposelearn.navitegation.NavitemScreen
import com.example.baisecomposelearn.screens.components.ScreenModel

@Composable
fun CustomExamplesScreen(navController: NavController) {
    val modo: Modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
    val context = LocalContext.current
    ScreenModel(navController = navController, isGoBack = false, content = {
        Button(modifier = modo,
            onClick = { navController.navigate(NavitemScreen.AnimatableDeleteScreen.route) }) {
            Text(
                text = stringResource(id = R.string.animatabledeletescreen),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.White
            )
        }
        Button(modifier = modo,
            onClick = { navController.navigate(NavitemScreen.PickDateScreen.route) }) {
            Text(
                text = stringResource(id = R.string.pickdatascreen),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.White
            )
        }

        Button(modifier = modo,
            onClick = { navController.navigate(NavitemScreen.CameraxScreen.route) }) {
            Text(
                text = stringResource(id = R.string.camerax),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.White
            )
        }
        Button(modifier = modo,
            onClick = { navController.navigate(NavitemScreen.AppWidgetScreen.route) }) {
            Text(
                text = stringResource(id = R.string.appwidgetscreen),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.White
            )
        }
        Button(modifier = modo,
            onClick = { navController.navigate(NavitemScreen.EmojiCompoatScreen.route) }) {
            Text(
                text = stringResource(id = R.string.emojicompoat),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.White
            )
        }
        Button(modifier = modo,
            onClick = {
                val sendIntent = Intent(context, VibrationActivity::class.java)
                ContextCompat.startActivity(context, sendIntent, Bundle.EMPTY)
            }) {
            Text(
                text = stringResource(id = R.string.vibration),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.White
            )
        }
        Button(modifier = modo,
            onClick = {
                navController.navigate(NavitemScreen.RatingBarScreen.route)
            }) {
            Text(
                text = stringResource(id = R.string.ratingbar),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.White
            )
        }
        Button(modifier = modo,
            onClick = {
                navController.navigate(NavitemScreen.CollapsingToolbar.route)
            }) {
            Text(
                text = stringResource(id = R.string.collapsingtoolbar),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.White
            )
        }
        Button(modifier = modo,
            onClick = {
                navController.navigate(NavitemScreen.SystemUiControllerAccompanistScreen.route)
            }) {
            Text(
                text = stringResource(id = R.string.systemuicontrolleraccompanist),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.White
            )
        }
        Button(modifier = modo,
            onClick = {
                navController.navigate(NavitemScreen.OrbitalExampleScreen.route)
            }) {
            Text(
                text = stringResource(id = R.string.orbitalexample),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.White
            )
        }
        Button(modifier = modo,
            onClick = {
                navController.navigate(NavitemScreen.CollapsingToolScreen.route)
            }) {
            Text(
                text = stringResource(id = R.string.collapsingtoolscreen),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.White
            )
        }
        Button(modifier = modo,
            onClick = {
                navController.navigate(NavitemScreen.ZoomAbleComposeImage.route)
            }) {
            Text(
                text = stringResource(id = R.string.zoomablecomposeimage),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.White
            )
        }
        Button(modifier = modo,
            onClick = {
                navController.navigate(NavitemScreen.SnapperScreen.route)
            }) {
            Text(
                text = stringResource(id = R.string.snapperscreen),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.White
            )
        }
    })
}

@Preview
@Composable
fun CustomExamplesScreenPreview() {
    CustomExamplesScreen(navController = rememberNavController())
}