package com.example.baisecomposelearn.screens.components

import android.graphics.PointF
import androidx.compose.ui.graphics.Color
import com.example.baisecomposelearn.theme.rwGreen
import java.text.SimpleDateFormat
import java.util.*

val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).apply {
    timeZone = TimeZone.getTimeZone("Asia/Shanghai")
}

fun xCoordination(width: Float, x: Float, totalCount: Int): Float {
    return width * (x / (totalCount - 1))
}

fun yCoordination(height: Float, y: Float, maxValue: Float): Float {
    return height - y * (height / maxValue) * 0.95f
}

val colorGroup = listOf(Color(0xff00ffcc), Color.Red, rwGreen)

val times = ArrayList<String>().apply {
    val baseTime = GregorianCalendar.getInstance()
    val dateFormat = SimpleDateFormat("HH:mm:ss", Locale.CHINA).apply {
        timeZone = TimeZone.getTimeZone("Asia/Shanghai")
    }
    for (index in 0 until 100) {
        baseTime.add(Calendar.MINUTE, 1)
        add(dateFormat.format(baseTime.time))
    }
}
val lineChartData = ArrayList<PointF>().apply {
    for (index in 0 until 100) {
        val r = Random(System.currentTimeMillis())
        add(PointF(index.toFloat(), (5 * index - r.nextInt(10)).toFloat()))
    }
}