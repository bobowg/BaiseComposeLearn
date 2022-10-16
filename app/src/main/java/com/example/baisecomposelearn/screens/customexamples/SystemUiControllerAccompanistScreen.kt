package com.example.baisecomposelearn.screens.customexamples

import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
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
import com.example.baisecomposelearn.theme.colors
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SystemUiControllerAccompanistScreen(
    navController: NavController
) {
    ScreenModel(navController = navController, content = {
        SystemUiControllerExample(
            navController = navController,
            systemUiController = rememberSystemUiController()
        )
    })
}

@Composable
fun SystemUiControllerExample(
    navController: NavController,
    systemUiController: SystemUiController
) {
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current
    val useDarkIcons = MaterialTheme.colors.isLight
    val originalStatusBarColor = MaterialTheme.colors.primaryVariant
    DisposableEffect(Unit) {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                systemUiController.setSystemBarsColor(
                    color = originalStatusBarColor,
                    darkIcons = !useDarkIcons
                )
                navController.popBackStack()
            }
        }
        backDispatcher?.onBackPressedDispatcher?.addCallback(callback)
        onDispose {
            callback.remove()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                systemUiController.setStatusBarColor(
                    color = Color(colors.random()),
                    darkIcons = useDarkIcons
                )
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.change_system_bar_colors),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.White
            )
        }
        Button(
            onClick = {
                systemUiController.setStatusBarColor(
                    color = originalStatusBarColor,
                    darkIcons = !useDarkIcons
                )
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.restore_system_bar_colors),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.White
            )
        }
        Button(
            onClick = {
                systemUiController.setStatusBarColor(
                    color = Color(colors.random()),
                    darkIcons = useDarkIcons
                )
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.change_navigation_bar_colors),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.White
            )
        }
        Button(
            onClick = {
                systemUiController.setNavigationBarColor(
                    color = originalStatusBarColor,
                    darkIcons = !useDarkIcons
                )
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = stringResource(id = R.string.restore_navigation_bar_colors))
        }
    }
}

@Preview
@Composable
fun SystemUiControllerAccompanistScreenPreivew() {
    SystemUiControllerAccompanistScreen(navController = rememberNavController())
}