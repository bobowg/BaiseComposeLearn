package com.example.baisecomposelearn.screens.media

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.baisecomposelearn.model.playviewmodel.PlayViewModel
import com.example.baisecomposelearn.utils.NormalVideoListScreen
import com.example.baisecomposelearn.utils.RefreshVideoListScreen


@Composable
fun Media3ExoplayerScreen() {
    val viewModel = PlayViewModel()
    val loadModeRefresh = true
    val context = LocalContext.current
    if (loadModeRefresh){
        RefreshVideoListScreen(viewModel = viewModel, context = context)
    }else{
        NormalVideoListScreen(viewModel = viewModel, context = context)
    }
}
