package com.example.baisecomposelearn.screens.components

import android.graphics.Path
import android.graphics.PointF
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.math.*


@Composable
fun LineChart(
    times: List<String>,
    color: List<Color>,
    vararg data: List<PointF>,
    legends: List<String>,
    chartTitle: String = ""
) {
    if (times.isEmpty()) return
    //数据初始化，只运行一次
    LaunchedEffect(Unit) {
        if (chartTitle.contains("效率")) {
            data.forEach {
                val efficiencyData = it.map { d -> PointF(d.x, d.y - 0.8f) }
                val arrayList = it as ArrayList<PointF>
                arrayList.clear()
                arrayList.addAll(efficiencyData)
            }
        }
    }
    Card(
        shape = RoundedCornerShape(10),
        elevation = 10.dp,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1.618f)
            .padding(15.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text(chartTitle)
        }
        var position by remember {
            mutableStateOf(Offset(0f, 0f))
        }
        var showDetail by remember {
            mutableStateOf(false)
        }
        var key = 1
        Canvas(modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(onPress = { offset ->
                    val handle = key + 1
                    key += 1
                    showDetail = true
                    position = offset
                    delay(5000L)
                    if (key == handle) showDetail = false
                })

            }) {
            val height = size.height - 16.dp.toPx()
            val width = size.width
            var maxValue = data.map { it.maxOf { d -> d.y } }.maxOf { f -> f }
            if (chartTitle.contains("效率")) {
                maxValue = 0.2f
            }
            //初始化画笔
            val paint = Paint()
            val frameworkPaint = paint.asFrameworkPaint()
            frameworkPaint.run {
                isAntiAlias = true
                textSize = 8.dp.toPx()
                this.color = Color.Black.toArgb()
            }
            data.forEachIndexed first@{ index, it ->
                if (it.isEmpty()) return@first
                val path = Path()
                path.moveTo(
                    xCoordination(width, it[0].x, it.size),
                    yCoordination(height, it[0].y, maxValue)
                )
                var controlX = xCoordination(width, it[0].x, it.size)
                var controlY = yCoordination(height, it[0].y, maxValue)
                it.forEachIndexed second@{ index1, dataPoint ->
                    if (index1 == 0) return@second
                    val endX = (controlX + xCoordination(width, dataPoint.x, it.size)) / 2
                    val endY = (controlY + yCoordination(height, dataPoint.y, maxValue)) / 2
                    path.quadTo(controlX, controlY, endX, endY)
                    controlX = xCoordination(width, dataPoint.x, it.size)
                    controlY = yCoordination(height, dataPoint.y, maxValue)
                }
                path.lineTo(
                    xCoordination(width, it[it.size - 1].x, it.size),
                    yCoordination(height, it[it.size - 1].y, maxValue)
                )
                drawPath(
                    path.asComposePath(),
                    color = color[index],
                    style = Stroke(width = 4f, cap = StrokeCap.Round)
                )
                path.lineTo(width, height)
                path.lineTo(
                    xCoordination(width, it[0].x, it.size),
                    height
                )
                path.close()
                drawPath(
                    path.asComposePath(),
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            color[index].copy(alpha = color[index].alpha / 2),
                            Color.Transparent
                        )
                    )
                )
            }
            //上面绘制的都是曲线，下面绘制横轴的时间
            drawIntoCanvas {
                //确定没一个x坐标的位置
                val xLabelCount = if (times.size > 8) 8 else times.size
                val indexes = (1 until xLabelCount).map { n -> times.size / xLabelCount * n }
                val labels = times.filterIndexed { index, _ -> index in indexes }
                    .map { t -> t.substring(0 until 5) }
                val labelPositions = (1 until xLabelCount).map { n ->
                    PointF(
                        size.width / xLabelCount * n,
                        size.height - 6.dp.toPx()
                    )
                }
                //将x轴信息绘制到底部
                labelPositions.forEachIndexed { index, pointF ->
                    it.nativeCanvas.drawText(
                        labels[index],
                        pointF.x - frameworkPaint.measureText(labels[index]) / 2,
                        pointF.y,
                        frameworkPaint
                    )
                }
            }
            //然后是纵轴信息
            val yLabels: List<Int>
            var power = floor(log(maxValue, 10f)).toInt()
            var factor = maxValue / 10.0.pow(power.toDouble())
            if (factor < 4) {
                factor *= 10
                power -= 1
                yLabels = (1..7).map {
                    (factor / 8 * it).roundToInt()
                }
            } else {
                yLabels = (1..factor.toInt()).map {
                    (factor / (factor.toInt() + 1) * it).roundToInt()
                }
            }

            val yPositions: List<Float> =
                yLabels.map {
                    yCoordination(
                        height,
                        it * 10.0.pow(power.toDouble()).toFloat(),
                        maxValue
                    )
                }
            yLabels.forEachIndexed { yLabelIndex, yLabel ->
                drawLine(
                    brush = Brush.horizontalGradient(
                        listOf(
                            Color.LightGray,
                            Color.LightGray
                        )
                    ),
                    start = Offset(0f, yPositions[yLabelIndex]),
                    end = Offset(width, yPositions[yLabelIndex]),
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(5f, 5f))
                )
                drawIntoCanvas {
                    it.nativeCanvas.drawText(
                        if (power >= 0) (yLabel * 10.0.pow(power.toDouble())
                            .toInt()).toString() else String.format(
                            "%.${abs(power)}f",
                            (if (chartTitle.contains("效率")) yLabel + 80
                            else yLabel) * 10.0.pow(
                                power.toDouble()
                            )
                        ),
                        0f,
                        yPositions[yLabelIndex],
                        frameworkPaint
                    )
                }
            }
            //绘制手指按上去之后显示的具体信息
            if (showDetail) {
                //先找到手指所在点的横纵坐标
                val detailTimeIndex = (position.x / width * (times.size - 1)).roundToInt()
                if (detailTimeIndex > times.size - 1) {
                    throw IllegalArgumentException("点击的位置超过了图表的宽度")
                }

                val detailTime = times[detailTimeIndex]
                val detailValues = data.map { d -> d[detailTimeIndex] }
                drawLine(
                    color = Color.Blue,
                    start = Offset(position.x, 0f),
                    end = Offset(position.x, height),
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(5f, 5f))
                )
                detailValues.forEachIndexed { index, dataPoint ->
                    drawLine(
                        color[index],
                        start = Offset(0f, yCoordination(height, dataPoint.y, maxValue)),
                        end = Offset(
                            width,
                            yCoordination(height, dataPoint.y, maxValue)
                        ),
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(5f, 5f))
                    )
                }
                //显示具体信息
                val avgY = yCoordination(
                    height,
                    detailValues.sumOf { it.y.toDouble() }.toFloat() / detailValues.size,
                    maxValue
                )
                val topLeft = when {
                    position.x <= width * 0.5f && avgY <= height * 0.5f -> {
                        Offset(position.x, avgY)
                    }
                    position.x > width * 0.5f && avgY <= height * 0.5f -> {
                        Offset(position.x - 200f, avgY)
                    }
                    position.x <= width * 0.5f && avgY > height * 0.5f -> {
                        Offset(position.x, avgY - 130f)
                    }
                    position.x > width * 0.5f && avgY > height * 0.5f -> {
                        Offset(position.x - 200f, avgY - 130f)
                    }
                    else -> Offset(0f, 0f)
                }
                //初始化写入文字要用到的画笔
                frameworkPaint.run {
                    this.color = Color.Blue.toArgb()
                    textSize = 12.dp.toPx()
                }
                //创建所有要显示的具体信息
                val messages = legends.mapIndexed { index, s ->
                    "$s：${detailValues[index].y}"
                }
                //计算信息框的宽度，长度
                val messageBoxWidth = messages.maxOf { m -> frameworkPaint.measureText(m) } + 30f
                val messageBoxHeight = (15 + 15 * data.size).dp.toPx()
                //画出信息框
                drawRoundRect(
                    Color.White,
                    topLeft = topLeft,
                    size = Size(messageBoxWidth, messageBoxHeight),
                    cornerRadius = CornerRadius(25f, 25f),
                    style = androidx.compose.ui.graphics.drawscope.Fill
                )
                drawRoundRect(
                    Color.Blue,
                    topLeft = topLeft,
                    size = Size(messageBoxWidth, messageBoxHeight),
                    cornerRadius = CornerRadius(25f, 25f),
                    style = Stroke(width = 1f)
                )

                drawIntoCanvas {
                    it.nativeCanvas.drawText(
                        detailTime,
                        topLeft.x + 10f,
                        topLeft.y + 10.dp.toPx(),
                        frameworkPaint
                    )

                    detailValues.forEachIndexed { index, _ ->
                        frameworkPaint.color = color[index].toArgb()
                        it.nativeCanvas.drawText(
                            messages[index],
                            topLeft.x + 10f,
                            topLeft.y + (10 + 14 * (index + 1)).dp.toPx(),
                            frameworkPaint
                        )
                    }
                }
            }
        }
    }
}