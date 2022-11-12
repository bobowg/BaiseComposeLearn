package com.example.baisecomposelearn.screens.constraintlayout


import android.widget.CalendarView
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.screens.components.ScreenModel


@Composable
fun CustomCalendarView(navController: NavController) {
    ScreenModel(navController = navController, content = {
        AndroidView(factory = { context ->
            CalendarView(context)
        })
    })
}

@Preview
@Composable
fun CalendarViewPreview() {
    CustomCalendarView(navController = rememberNavController())
}