package com.example.baisecomposelearn.screens.constraintlayout

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.screens.components.ScreenModel

@Composable
fun DecoupledScreen(navController: NavController) {
    ScreenModel(navController = navController, content = {
        DecoupledConstraintLayout()
        Spacer(modifier = Modifier.height(100.dp))
    })
}

@Composable
fun DecoupledConstraintLayout() {
    BoxWithConstraints {
        val constraints = if (minWidth < 600.dp) {
            decoupledConstraints(margin = 16.dp)
        } else {
            decoupledConstraints(margin = 32.dp)
        }
        ConstraintLayout(constraints) {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .layoutId("button")
                    .align(Alignment.Center)
            ) {
                Text(
                    text = stringResource(id = R.string.decoupled),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
            Text(
                text = stringResource(id = R.string.decoupled),
                modifier = Modifier
                    .layoutId("text")
                    .align(Alignment.Center)
                    .padding(bottom = 16.dp),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

        }
    }
}

private fun decoupledConstraints(margin: Dp): ConstraintSet =
    ConstraintSet {
        val button = createRefFor("button")
        val text = createRefFor("text")
        constrain(button) {
            top.linkTo(parent.top, margin = margin)
        }
        constrain(text) {
            top.linkTo(parent.bottom, margin = margin)
        }
    }

@Preview
@Composable
fun DecoupledScreenPreview() {
    DecoupledScreen(navController = rememberNavController())
}