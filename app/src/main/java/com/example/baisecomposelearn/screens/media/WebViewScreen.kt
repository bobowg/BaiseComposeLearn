package com.example.baisecomposelearn.screens.media

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.appdrawer.DefaultTopAppBar
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState

@Composable
fun WebViewScreen(navController: NavController) {
    val state = rememberWebViewState(url = "https://github.com/bobowg/BaiseComposeLearn")
    Scaffold(
        topBar = {
            DefaultTopAppBar(
                navController = navController,
                title = stringResource(id = R.string.webview)
            )
        },
        content = { PaddingValues ->
            WebView(state = state, modifier = Modifier.padding(PaddingValues))
        },
        contentColor = MaterialTheme.colors.primary
    )
}
