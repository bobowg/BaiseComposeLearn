package com.example.baisecomposelearn.screens.constraintlayout

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.screens.components.ScreenModel
import com.example.baisecomposelearn.theme.darkTextColorSecondary
import com.example.baisecomposelearn.theme.rwGreen
import com.example.baisecomposelearn.theme.rwGreenDark
import com.example.baisecomposelearn.theme.rwRed
import com.example.baisecomposelearn.utils.TicketShape
import com.example.baisecomposelearn.utils.drawTicketPath

@Composable
fun CustomShape(navController: NavController) {
    ScreenModel(navController = navController, content = {
        Text(
            text = stringResource(id = R.string.timesup),
            style = typography.h3.copy(color = rwRed),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp)
                .border(width = 4.dp, color = rwRed, CutCornerShape(32.dp))
                .graphicsLayer {
                    shadowElevation = 8.dp.toPx()
                    shape = CutCornerShape(32.dp)
                    clip = true
                }
                .background(color = rwGreen)
                .padding(32.dp)
        )
        TicketComposable()
    })
}

@Composable
fun TicketComposable(modifier: Modifier = Modifier) {
    Text(
        text = "🎉 电影票 🎉",
        style = TextStyle(
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        ),
        textAlign = TextAlign.Center,
        modifier = modifier
            .wrapContentSize()
            .graphicsLayer {
                shadowElevation = 8.dp.toPx()
                shape = TicketShape(24.dp.toPx())
                clip = true
            }
            .background(color = rwRed)
            .drawBehind {
                scale(scale = 0.9f) {
                    drawPath(
                        path = drawTicketPath(size = size, cornerRadius = 24.dp.toPx()),
                        color = rwGreenDark,
                        style = Stroke(
                            width = 2.dp.toPx(),
                            pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f))
                        )
                    )
                }
            }
            .padding(start = 72.dp, top = 64.dp, end = 72.dp, bottom = 64.dp)
    )
}

@Preview
@Composable
fun CustomShapePreview() {
    CustomShape(navController = rememberNavController())
}