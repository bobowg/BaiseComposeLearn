package com.example.baisecomposelearn.screens.animate

import android.annotation.SuppressLint
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun CustomCountDownTimer() {
    MyTimer()
}

private enum class Screen {
    Input, Countdown
}

@SuppressLint("UnusedCrossfadeTargetStateParameter")
@Composable
private fun MyTimer() {
    var timeInSec = 0
    Surface(color = MaterialTheme.colors.background) {
        var screen by remember { mutableStateOf(Screen.Input) }
        Crossfade(targetState = screen) {
            when (screen) {
                Screen.Input -> InputScreen {
                    timeInSec = it
                    screen = Screen.Countdown
                }
                Screen.Countdown -> CountdownScreen(timeInSec) {
                    screen = Screen.Input
                }
            }
        }
    }
}

@Preview
@Composable
fun CustomCountDownTimerPrivew() {
    CustomCountDownTimer()
}