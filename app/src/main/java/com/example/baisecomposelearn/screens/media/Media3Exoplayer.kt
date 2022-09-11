package com.example.baisecomposelearn.screens.media

import android.net.Uri
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.datasource.DataSource
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import com.example.baisecomposelearn.model.playviewmodel.PlayViewModel
import com.example.baisecomposelearn.utils.NormalVideoListScreen
import com.example.baisecomposelearn.utils.RefreshVideoListScreen


@Composable
fun Media3ExoplayerScreen() {
    val viewModel:PlayViewModel = PlayViewModel()
    val loadModeRefresh = false
    val context = LocalContext.current
    if (loadModeRefresh){
        RefreshVideoListScreen(viewModel = viewModel, context = context)
    }else{
        NormalVideoListScreen(viewModel = viewModel, context = context)
    }
}
