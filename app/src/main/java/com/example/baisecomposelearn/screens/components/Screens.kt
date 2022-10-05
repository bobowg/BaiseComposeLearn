package com.example.baisecomposelearn.screens.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.baisecomposelearn.theme.DrawerButtonShape

@Composable
fun Screen(
    pageTitle: String,
    titlePadding: PaddingValues = PaddingValues(start = 16.dp, top = 32.dp),
    screenPadding: PaddingValues = PaddingValues(0.dp),
    messageToUser: String = "",
    scrollState: ScrollState = rememberScrollState(),
    content: @Composable () -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(screenPadding)
            .verticalScroll(scrollState),
    ) {
        Box(modifier = Modifier.weight(1f)) {
            Column() {
                Text(
                    text = pageTitle,
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier.padding(titlePadding)
                )
                MessageBar(message = messageToUser)
                content()
            }
        }
    }
}

/**
 * An animated message bar.
 */
@Composable
private fun MessageBar(message: String) {
    AnimatedVisibility(
        visible = message.isNotEmpty(),
        enter = slideInVertically(
            initialOffsetY = { fullHeight -> -fullHeight },
            animationSpec = tween(durationMillis = 150, easing = LinearOutSlowInEasing)
        ),
        exit = slideOutVertically(
            targetOffsetY = { fullHeight -> -fullHeight },
            animationSpec = tween(durationMillis = 250, easing = FastOutLinearInEasing)
        )
    ) {
        Surface(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            shape = DrawerButtonShape,
            color = MaterialTheme.colors.secondary,
            elevation = 4.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(message)
            }
        }
    }
}