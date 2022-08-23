package com.example.baisecomposelearn.screens.constraintlayout

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.screens.components.ScreenModel

@Composable
fun BrrierScreen(navController: NavController) {
    ScreenModel(
        navController = navController,
        content = {
           ConstraintLayoutContent()
        }
    )
}

@Composable
fun ConstraintLayoutContent() {
    ConstraintLayout {

        val (button, text) = createRefs()

        Button(
            onClick = { /* Do something */ },
            modifier = Modifier.constrainAs(button) {
                top.linkTo(parent.top, margin = 16.dp)
            }
        ) {
            Text(text = stringResource(id = R.string.barrier), fontSize = 24.sp)
        }

        Text(text = stringResource(id = R.string.barrier), Modifier.constrainAs(text) {
            top.linkTo(button.bottom, margin = 16.dp)
        }, fontSize = 24.sp, color = Color.White)
    }
}

@Preview
@Composable
fun BarrierScreenPreview() {
    BrrierScreen(navController = rememberNavController())
}