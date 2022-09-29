package com.example.baisecomposelearn.screens.activate

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
fun BackHandlersScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.colorPrimary))
            .wrapContentSize(Alignment.Center)
    ) {
        Button(
            onClick = {
                navController.navigate(NavitemScreen.Backhandler.route)
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.backhandlers),
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                color = Color.White
            )
        }
        Button(
            onClick = { navController.navigate(NavitemScreen.LanunScreen1.route) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.launch1),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.White
            )
        }
        Button(
            onClick = { navController.navigate(NavitemScreen.LanunScreen2.route) },
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.launch2),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.White
            )
        }
        Button(
            onClick = { navController.navigate(NavitemScreen.LanunScreen3.route) },
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.googlemap),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.White
            )
        }
        Button(
            onClick = { navController.navigate(NavitemScreen.FlashLightScreen.route) },
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.flashlight),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.White
            )
        }
    }
    
}

@Preview
@Composable
fun BackHandlersScreenPreview() {
    val navController = rememberNavController()
    BackHandlersScreen(navController)
}