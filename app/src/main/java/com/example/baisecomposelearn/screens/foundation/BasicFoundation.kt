package com.example.baisecomposelearn.screens.foundation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.navitegation.NavitemScreen

@Composable
fun BasicFoundation(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.colorPrimary))
            .wrapContentSize(Alignment.Center)
    ) {
        Button(
            onClick = {
                navController.navigate(NavitemScreen.TextFileAndButton.route)
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.textfileandbutton),
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                color = Color.White
            )
        }
        Button(
            onClick = {
                navController.navigate(NavitemScreen.DynamicIsland.route)
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.dynamicisland),
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                color = Color.White
            )
        }
        Button(
            onClick = {
                navController.navigate(NavitemScreen.Paginationcomponent.route)
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.paginationcomponent),
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                color = Color.White
            )
        }
        Button(
            onClick = {
                navController.navigate(NavitemScreen.Multi_Layer_Parallax.route)
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.multi_layer_parallax),
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                color = Color.White
            )
        }
        Button(
            onClick = {
                navController.navigate(NavitemScreen.GuideToLazy.route)
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.guidtolayz),
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                color = Color.White
            )
        }
        Button(
            onClick = {
                navController.navigate(NavitemScreen.BackgroundScreen.route)
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.backgroundscreen),
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                color = Color.White
            )
        }
        Button(
            onClick = {
                navController.navigate(NavitemScreen.LoginUserScreen.route)
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.userlogin),
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                color = Color.White
            )
        }
        Button(
            onClick = {
                navController.navigate(NavitemScreen.CacheDirScreen.route)
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.cachedir),
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                color = Color.White
            )
        }
    }
}

@Preview
@Composable
fun BasicFoundactionPreview() {
    BasicFoundation(navController = rememberNavController())
}