package com.example.baisecomposelearn.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import com.example.baisecomposelearn.navitegation.NavitemScreen
import com.example.baisecomposelearn.screens.components.ScreenModel

@Composable
fun AnimationScreen(navController: NavController) {
    val modo = Modifier
        .fillMaxWidth()
        .padding(16.dp)
    ScreenModel(
        navController = navController,
        isGoBack = false,
        content = {
            Button(modifier = modo,
                onClick = { navController.navigate(NavitemScreen.AnimatedScreen.route) }) {
                Text(
                    text = stringResource(id = R.string.animation1),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.White
                )
            }
            Button(modifier = modo,
                onClick = { /*TODO*/ }) {
                Text(
                    text = stringResource(id = R.string.animation),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.White
                )
            }
            Button(modifier = modo,
                onClick = { /*TODO*/ }) {
                Text(
                    text = stringResource(id = R.string.animation),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.White
                )
            }
            Button(modifier = modo,
                onClick = { /*TODO*/ }) {
                Text(
                    text = stringResource(id = R.string.animation),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.White
                )
            }
            Button(modifier = modo,
                onClick = { /*TODO*/ }) {
                Text(
                    text = stringResource(id = R.string.animation),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.White
                )
            }
            Button(modifier = modo,
                onClick = { /*TODO*/ }) {
                Text(
                    text = stringResource(id = R.string.animation),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.White
                )
            }
            Button(modifier = modo,
                onClick = { /*TODO*/ }) {
                Text(
                    text = stringResource(id = R.string.animation),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.White
                )
            }
            Button(modifier = modo,
                onClick = { /*TODO*/ }) {
                Text(
                    text = stringResource(id = R.string.animation),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.White
                )
            }
            Button(modifier = modo,
                onClick = { /*TODO*/ }) {
                Text(
                    text = stringResource(id = R.string.animation),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.White
                )
            }
            Button(modifier = modo,
                onClick = { /*TODO*/ }) {
                Text(
                    text = stringResource(id = R.string.animation),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.White
                )
            }
            Button(modifier = modo,
                onClick = { /*TODO*/ }) {
                Text(
                    text = stringResource(id = R.string.animation),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.White
                )
            }
            Button(modifier = modo,
                onClick = { /*TODO*/ }) {
                Text(
                    text = stringResource(id = R.string.animation),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.White
                )
            }
        }
    )
}

@Preview
@Composable
fun AnimationScreenPreview() {
    AnimationScreen(navController = rememberNavController())
}