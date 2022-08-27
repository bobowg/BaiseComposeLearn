package com.example.baisecomposelearn.screens.constraintlayout

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
fun ConstraintLayoutScreen(navController: NavController) {
   val modo: Modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
    ScreenModel(navController = navController, isGoBack = false, content = {
        Button(modifier = modo,
            onClick = { navController.navigate(NavitemScreen.BarrierScreen.route) }) {
            Text(
                text = stringResource(id = R.string.barrier),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.White
            )
        }
        Button(modifier = modo,
            onClick = { navController.navigate(NavitemScreen.DecoupledScreen.route) }) {
            Text(
                text = stringResource(id = R.string.decoupled),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.White
            )
        }
        Button(modifier = modo,
            onClick = { navController.navigate(NavitemScreen.CircularScreen.route) }) {
            Text(
                text = stringResource(id = R.string.circularscreen),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.White
            )
        }
        Button(modifier = modo,
            onClick = { navController.navigate(NavitemScreen.JsonConstraintScreen.route) }) {
            Text(
                text = stringResource(id = R.string.jsonconstraint),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.White
            )
        }
        Button(modifier = modo,
            onClick = { navController.navigate(NavitemScreen.MotionLayoutScreen.route) }) {
            Text(
                text = stringResource(id = R.string.motionlayout),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.White
            )
        }
        Button(modifier = modo,
            onClick = { navController.navigate(NavitemScreen.AuthenticationScreen.route) }) {
            Text(
                text = stringResource(id = R.string.notication,""),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.White
            )
        }
        Button(modifier = modo,
            onClick = { navController.navigate(NavitemScreen.TabsScreen.route) }) {
            Text(
                text = stringResource(id = R.string.tabs,""),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.White
            )
        }
    })
}

@Preview
@Composable
fun ConstraintLayoutScreenPreview() {
    ConstraintLayoutScreen(navController = rememberNavController())
}