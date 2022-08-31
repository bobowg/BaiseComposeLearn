package com.example.baisecomposelearn.screens.components

import android.graphics.Point
import android.util.Log
import androidx.compose.animation.core.FloatTweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp

private fun identifyClickItem(points: List<Point>, x: Float, y: Float): Int {
    for ((index, point) in points.withIndex()) {
        if (x > point.x + 20 && x < point.x + 20+40) {
            return index
        }
    }
    return -1
}

@Composable
fun BarChart() {
    val point = listOf(
        Point(10, 10), Point(90, 100), Point(170, 30),
        Point(250, 200), Point(330, 120), Point(410, 10),
        Point(490, 280), Point(570, 100), Point(650, 10),
        Point(730, 100), Point(810, 200)
    )
    var start by remember { mutableStateOf(false) }
    val heightPre by animateFloatAsState(
        targetValue = if (start) 1f else 0f,
        animationSpec = FloatTweenSpec(duration = 1000)
    )
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(250.dp)
            .clip(CutCornerShape(10))
            .background(Color.White)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        val i = identifyClickItem(point, it.x, it.y)
                        Log.d("pointerInput", "onTap: ${it.x} ${it.y} item:$i")
                    }
                )
            }
    ) {
        //绘制 X轴 Y轴
        drawLine(
            start = Offset(10f, 600f),
            end = Offset(10f, 0f),
            color = Color.Black,
            strokeWidth = 2f
        )
        drawLine(
            start = Offset(10f, 600f),
            end = Offset(850f, 600f),
            color = Color.Black,
            strokeWidth = 2f
        )
        start = true
        for (p in point) {
            drawRect(
                color = Color.Blue,
                topLeft = Offset((p.x + 20).toFloat(), 600 - (600 - p.y) * heightPre),
                size = Size(40f, (600 - p.y) * heightPre)
            )
        }
    }
}