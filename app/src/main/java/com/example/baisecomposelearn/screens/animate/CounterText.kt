@file:OptIn(ExperimentalAnimationApi::class)

package com.example.baisecomposelearn.screens.animate

import androidx.compose.animation.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.screens.components.ScreenModel
import com.example.baisecomposelearn.theme.surfaceHeader
import kotlinx.coroutines.*

@Composable
fun CounterText(navController: NavController) {
    val scope = CoroutineScope(Dispatchers.Main)
    ScreenModel(navController = navController, content = {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var count by remember { mutableStateOf(0) }
            Counter(
                count = count,
                style = MaterialTheme.typography.h1
            )
            Button(onClick = {
                scope.launch {
                    for (i in 1..100) {
                        delay(1000L)
                        count++
                    }
                }
            }) {
                Text(text = "计时")
            }
        }
    })
}

@Composable
fun Counter(
    count: Int,
    modifier: Modifier = Modifier,
    style: androidx.compose.ui.text.TextStyle = MaterialTheme.typography.body1
) {
    var oldCount by remember { mutableStateOf(count) }
    SideEffect {
        oldCount = count
    }
    Row(
        modifier = modifier
    ) {
        val countString = count.toString()
        val oldCountString = oldCount.toString()
        for (i in countString.indices) {
            val oldChar = oldCountString.getOrNull(i)
            val newChar = countString[i]
            val char = if (oldChar == newChar) {
                oldCountString[i]
            } else {
                countString[i]
            }
            AnimatedContent(
                targetState = char,
                transitionSpec = {
                    slideInVertically { it } with slideOutVertically { -it }
                }
            ) { chat ->
                Text(
                    text = chat.toString(),
                    style = style,
                    color = surfaceHeader,
                    softWrap = false
                )
            }
        }
    }
}

@Preview
@Composable
fun CounterTextPreview() {
    CounterText(navController = rememberNavController())
}