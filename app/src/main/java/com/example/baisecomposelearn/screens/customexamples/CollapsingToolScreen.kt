package com.example.baisecomposelearn.screens.customexamples

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.appdrawer.DefaultTopAppBar

private const val MinHeight = 56f
private const val MaxHeight = 168f

@Composable
fun CollapsingToolScreen(navController: NavController) {
    val scrollState = rememberScrollState(0)
    Scaffold(
        topBar = {
            TopScrollingContent(scrollState, navController)
        }
    ) { Padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(Padding)
                .verticalScroll(scrollState)
        ) {
            repeat(5) {
                Text(text = stringResource(id = R.string.content))
            }
        }
    }
}

@Composable
private fun TopScrollingContent(scrollState: ScrollState, navController: NavController) {
    val dynamicHeight = (MaxHeight - scrollState.value).coerceIn(MinHeight, MaxHeight)
    DefaultTopAppBar(
        navController = navController,
        title = stringResource(id = R.string.collapsingtoolscreen),
        modifier = Modifier.heightIn(min = animateDpAsState(targetValue = dynamicHeight.dp).value)
    )
}