package com.example.baisecomposelearn.screens.components

import android.graphics.Region
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
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp

private fun getPositionFromAngle(angles: List<Float>, touchAngle: Double): Int {
    var totalAngle = 0f
    for ((i, angle) in angles.withIndex()) {
        totalAngle += angle
        if (touchAngle <= totalAngle) {
            return i
        }
    }
    return -1
}
@Composable
fun PieChart() {
    val point = listOf(10f, 40f, 20f, 80f, 100f, 60f)
    val color = listOf(Color.Blue, Color.Yellow, Color.Green, Color.Gray, Color.Red, Color.Cyan)
    val sum = point.sum()
    var startAngle = 0f
    val radius = 250f
    val rect = Rect(Offset(-radius, -radius), Size(2 * radius, 2 * radius))
    val path = Path()
    val angles = mutableListOf<Float>()
    val regions = mutableListOf<Region>()
    var start by remember { mutableStateOf(false) }
    val sweepPre by animateFloatAsState(
        targetValue = if (start) 1f else 0f,
        animationSpec = FloatTweenSpec(duration = 1000)
    )
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(200.dp)
            .clip(CutCornerShape(10))
            .background(Color.White)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        Log.d(
                            "pointerInput",
                            "onTap: ${it.x - radius.toInt()} ${it.y - radius.toInt()} ${regions}"
                        )
                        var x = it.x - radius
                        var y = it.y - radius
                        var touchAngle = Math.toDegrees(Math.atan2(y.toDouble(),x.toDouble()))
                        //坐标1,2象限返回-180~0  3,4象限返回0~180
                        if(x<0&&y<0 || x>0&&y<0){//1,2象限
                            touchAngle += 360;
                        }
                        val position = getPositionFromAngle(touchAngle = touchAngle,angles = angles)
                    }
                )
            }
    ) {
        translate(radius, radius) {
            start = true
            for ((i, p) in point.withIndex()) {
                var sweepAngle = p / sum * 360f
                println("sweepAngle: $sweepAngle  p:$p  sum:$sum")
                path.moveTo(0f, 0f)
                path.arcTo(rect = rect, startAngle, sweepAngle*sweepPre, false)
                angles.add(sweepAngle)
                drawPath(path = path, color = color[i])
                path.reset()
                startAngle += sweepAngle
            }
        }
    }
}