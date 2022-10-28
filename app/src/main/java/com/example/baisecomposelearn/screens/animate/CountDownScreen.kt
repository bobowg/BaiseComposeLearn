package com.example.baisecomposelearn.screens.animate

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.screens.animate.HMSFontInfo.Companion.HMS
import com.example.baisecomposelearn.screens.animate.HMSFontInfo.Companion.MS
import com.example.baisecomposelearn.screens.animate.HMSFontInfo.Companion.S
import com.example.baisecomposelearn.theme.rwGreen
import com.example.baisecomposelearn.theme.rwGreenDark

@Composable
fun CountdownScreen(
    timeInSec: Int,
    onCancel: () -> Unit
) {

    var trigger by remember { mutableStateOf(timeInSec) }

    val elapsed by animateIntAsState(
        targetValue = trigger * 1000,
        animationSpec = tween(timeInSec * 1000, easing = LinearEasing)
    )

    DisposableEffect(Unit) {
        trigger = 0
        onDispose { }
    }

    Column(
        Modifier
            .fillMaxHeight()
            .padding(start = 10.dp, end = 10.dp)
    ) {

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp),
            text = stringResource(id = R.string.customcountdowntimer),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )

        Box {
            AnimationElapsedTime(elapsed)
            AnimationCircleCanvas(elapsed)
        }

        Spacer(modifier = Modifier.size(55.dp))

        Image(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(70.dp)
                .shadow(30.dp, shape = CircleShape)
                .clickable { onCancel() },
            imageVector = Icons.Default.Cancel,
            contentDescription = null,
            colorFilter = ColorFilter.tint(MaterialTheme.colors.primary)
        )
    }
}

@Composable
private fun BoxScope.AnimationElapsedTime(elapsed: Int) {

    val (hou, min, sec) = remember(elapsed / 1000) {
        val elapsedInSec = elapsed / 1000
        val hou = elapsedInSec / 3600
        val min = elapsedInSec / 60 - hou * 60
        val sec = elapsedInSec % 60
        Triple(hou, min, sec)
    }

    val mills = remember(elapsed) {
        elapsed % 1000
    }

    val onlySec = remember(hou, min) {
        hou == 0 && min == 0
    }

    val transition = rememberInfiniteTransition()

    val animatedFont by transition.animateFloat(
        initialValue = 1.5f,
        targetValue = 0.8f,
        animationSpec = infiniteRepeatable(tween(500), RepeatMode.Reverse)
    )

    val (size, labelSize, padding) = when {
        hou > 0 -> HMS
        min > 0 -> MS
        else -> S
    }

    Row(
        Modifier
            .align(Alignment.Center)
            .padding(start = padding, end = padding, top = 10.dp, bottom = 10.dp)
    ) {
        if (hou > 0) {
            DisplayTime(
                hou.formatTime(),
                "h",
                fontSize = size,
                labelSize = labelSize
            )
        }
        if (min > 0) {
            DisplayTime(
                min.formatTime(),
                "m",
                fontSize = size,
                labelSize = labelSize
            )
        }
        DisplayTime(
            if (onlySec) sec.toString() else sec.formatTime(),
            if (onlySec) "" else "s",
            fontSize = size * (if (onlySec && sec < 10 && mills != 0) animatedFont else 1f),
            labelSize = labelSize,
            textAlign = if (onlySec) TextAlign.Center else TextAlign.End
        )
    }

    Text(
        ".${(mills / 10).formatTime()}",
        Modifier
            .align(Alignment.BottomCenter)
            .padding(bottom = 80.dp),
        fontSize = 30.sp,
        fontFamily = FontFamily.Cursive,
        style =MaterialTheme.typography.subtitle1,
        color = MaterialTheme.colors.primary,
    )
}

@Composable
private fun BoxScope.AnimationCircleCanvas(durationMills: Int) {
    val transition = rememberInfiniteTransition()
    var trigger by remember { mutableStateOf(0f) }
    var isFinished by remember { mutableStateOf(false) }

    val animateTween by animateFloatAsState(
        targetValue = trigger,
        animationSpec = tween(
            durationMillis = durationMills,
            easing = LinearEasing
        ),
        finishedListener = {
            isFinished = true
        }
    )

    val animatedRestart by transition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(tween(1000), RepeatMode.Restart)
    )
    val animatedReverse by transition.animateFloat(
        initialValue = 1.05f,
        targetValue = 0.95f,
        animationSpec = infiniteRepeatable(tween(2000), RepeatMode.Reverse)
    )

    DisposableEffect(Unit) {
        trigger = 360f
        onDispose {}
    }

    val strokeRestart = Stroke(15f)
    val strokeReverse = Stroke(10f)
    val color = MaterialTheme.colors.primary
    val secondColor = MaterialTheme.colors.secondary
    Canvas(
        modifier = Modifier
            .align(Alignment.Center)
            .padding(16.dp)
            .size(350.dp)
    ) {
        val diameter = size.minDimension
        val radius = diameter / 2f
        val topLeftOffset = Offset(10f, 10f)
        val size = Size(radius * 2, radius * 2)

        if (!isFinished) {

            drawArc(
                color = color,
                startAngle = animatedRestart,
                sweepAngle = 150f,
                topLeft = topLeftOffset,
                size = size,
                useCenter = false,
                style = strokeRestart,
            )
        }

        drawCircle(
            color = secondColor,
            style = strokeReverse,
            radius = radius * if (isFinished) 1f else animatedReverse
        )

        drawArc(
            startAngle = 270f,
            sweepAngle = animateTween,
            brush = Brush.radialGradient(
                radius = radius,
                colors = listOf(
                    rwGreen.copy(0.3f),
                    rwGreenDark.copy(0.2f),
                    Color.White.copy(0.3f)
                ),
            ),
            useCenter = true,
            style = Fill,
        )
    }
}

@Preview
@Composable
fun DisplayPreview() {
    CountdownScreen(1000) {}
}

private fun Int.formatTime() = String.format("%02d", this)

private data class HMSFontInfo(val fontSize: TextUnit, val labelSize: TextUnit, val padding: Dp) {
    companion object {
        val HMS = HMSFontInfo(50.sp, 20.sp, 40.dp)
        val MS = HMSFontInfo(85.sp, 30.sp, 50.dp)
        val S = HMSFontInfo(150.sp, 50.sp, 55.dp)
    }
}