package com.example.baisecomposelearn.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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


@Composable
fun ScreenModel(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {},
    navController: NavController,
    isGoBack:Boolean = true
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.colorPrimary))
            .wrapContentSize(Alignment.Center)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        content()
        if (isGoBack){
            goBack(navController = navController)
        }

    }

}

@Composable
fun goBack(navController: NavController) {
    Button(
        onClick = { navController.navigateUp() },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.goback),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color.White
        )
    }
}

@Preview
@Composable
fun ScreenModelPreview() {
    ScreenModel(navController = rememberNavController(), content = {
        Button(onClick = { /*TODO*/ }, modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)) {
            Text(
                text = stringResource(id = R.string.app_name),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.White
            )
        }
    }, isGoBack = false)
}