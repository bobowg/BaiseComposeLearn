package com.example.baisecomposelearn.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.text.FontStyle
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.example.baisecomposelearn.R

class SimpleGlanceAppWidget : GlanceAppWidget() {
    @Composable
    override fun Content() {
        Text(
            text = "hello bobowg",
            style = TextStyle(
                color = ColorProvider(R.color.colorPrimary),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic
            )
        )
    }
}

class SimpleGlanceAppWidgetReceiver:GlanceAppWidgetReceiver(){
    override val glanceAppWidget: GlanceAppWidget = SimpleGlanceAppWidget()
}