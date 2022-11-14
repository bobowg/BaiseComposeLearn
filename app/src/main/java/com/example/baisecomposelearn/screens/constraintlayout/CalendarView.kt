package com.example.baisecomposelearn.screens.constraintlayout

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.screens.components.ScreenModel
import io.github.boguszpawlowski.composecalendar.StaticCalendar
import io.github.boguszpawlowski.composecalendar.day.NonSelectableDayState
import io.github.boguszpawlowski.composecalendar.header.MonthState
import java.time.DayOfWeek
import java.time.format.TextStyle
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CustomCalendarView(navController: NavController) {
    ScreenModel(navController = navController, content = {
        StaticCalendar(
            modifier = Modifier.animateContentSize(),
            showAdjacentMonths = false,
            firstDayOfWeek = DayOfWeek.SUNDAY,
            dayContent = { MyDate(dayState = it) },
            weekHeader = { WeekHeader(daysOfWeek = it)},
            monthHeader = { MonthHeader(monthState = it)},

        )
    })
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun MyDate(dayState: NonSelectableDayState) {
    Text(
        text = dayState.date.dayOfMonth.toString(),
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.h6
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun MonthHeader(monthState: MonthState) {
    Row {
        Text(monthState.currentMonth.year.toString(), style = MaterialTheme.typography.h3)
        Text(monthState.currentMonth.month.name, style = MaterialTheme.typography.h3)
        IconButton(onClick = { monthState.currentMonth = monthState.currentMonth.plusMonths(1) }) {
            Image(
                imageVector = Icons.Default.Star,
                colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface),
                contentDescription = "Next",
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun WeekHeader(daysOfWeek: List<DayOfWeek>) {
    Row {
        daysOfWeek.forEach { dayOfWeek ->
            Text(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentHeight(),
                text = dayOfWeek.getDisplayName(TextStyle.NARROW, Locale.ROOT),
                textAlign = TextAlign.Center
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun CalendarViewPreview() {
    CustomCalendarView(navController = rememberNavController())
}