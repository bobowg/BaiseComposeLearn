package com.example.baisecomposelearn.screens.activate

import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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

private const val MaxPressed = 4

@Composable
fun BackHandlerScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.colorPrimary))
            .wrapContentSize(Alignment.Center),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var backPressedCount by rememberSaveable { mutableStateOf(0) }
        BackHandler {
            if (backPressedCount >= MaxPressed)
                navController.popBackStack()
            else
                backPressedCount++
        }
        val dispather = LocalOnBackPressedDispatcherOwner.current!!.onBackPressedDispatcher
        Button(
            onClick = {
                dispather.onBackPressed()
            },
            modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
            Text(
                text = stringResource(id = R.string.backhandler_count, backPressedCount),
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                color = Color.White
            )
        }
        Text(
            modifier = Modifier
                .padding(top = 56.dp)
                .align(Alignment.CenterHorizontally),
            text = stringResource(id = R.string.backhandler_msg, MaxPressed),
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            color = Color.White
        )
    }
}

@Preview
@Composable
fun BackHandlerScreenPreview() {
    BackHandlerScreen(navController = rememberNavController())
}