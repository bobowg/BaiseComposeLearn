package com.example.baisecomposelearn.screens.animate

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.screens.components.ScreenModel
import com.example.baisecomposelearn.theme.rwRed

@Composable
fun CircularProgressbar(navController: NavController) {
    ScreenModel(navController = navController, content = {
        CircularProgressbar1()
        Spacer(modifier = Modifier.height(10.dp))
    })
}


@Composable
fun CircularProgressbar1(
    number: Float = 70f,
    numberStyle: TextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp
    ),
    size: Dp = 280.dp,
    indicatorThickness: Dp = 28.dp,
    animationDuration: Int = 1000,
    animationDelay: Int = 0,
    foregroundIndicatorColor: Color = rwRed,
    backgroundindicatorColor: Color = Color.LightGray.copy(alpha = 0.3f)
) {
    var numberR by remember { mutableStateOf(0f) }
    val animateNumber = animateFloatAsState(
        targetValue = numberR,
        animationSpec = tween(durationMillis = animationDuration, delayMillis = animationDelay)
    )
    LaunchedEffect(Unit) {
        numberR = number
    }
    Box(
        modifier = Modifier
            .padding(16.dp)
            .size(size = size),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(size = size)) {
            drawCircle(
                color = backgroundindicatorColor,
                radius = size.toPx() / 2,
                style = Stroke(width = indicatorThickness.toPx(), cap = StrokeCap.Round)
            )
            val sweepAngle = (animateNumber.value / 100) * 360

            drawArc(
                color = foregroundIndicatorColor,
                startAngle = -90f,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = Stroke(indicatorThickness.toPx(), cap = StrokeCap.Round)
            )
        }
        Text(text = (animateNumber.value).toInt().toString(), style = numberStyle)
        Spacer(modifier = Modifier.height(32.dp))
        ButtonProgressbar {
            numberR = (1..100).random().toFloat()
        }
    }
}

@Composable
private fun ButtonProgressbar(
    backgroundColor: Color = Color(0xFF35898f),
    onClickButton: () -> Unit
) {
    Button(
        onClick = { onClickButton() },
        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor)
    ) {
        Text(text = stringResource(id = R.string.animate), color = Color.White, fontSize = 16.sp)
    }
}

@Preview
@Composable
fun CircularProgressbarPreview() {
    CircularProgressbar(navController = rememberNavController())
}