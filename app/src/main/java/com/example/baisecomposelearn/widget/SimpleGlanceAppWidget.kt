package com.example.baisecomposelearn.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.text.Text
import com.example.baisecomposelearn.R

class SimpleGlanceAppWidget : GlanceAppWidget() {
    @Composable
    override fun Content() {
        Text(
            text = stringResource(id = R.string.app_name)
        )
    }
}