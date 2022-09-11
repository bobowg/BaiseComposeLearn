package com.example.baisecomposelearn.screens.components.exoplayer

import android.net.Uri
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.ui.PlayerView
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberAsyncImagePainter
import com.example.baisecomposelearn.model.playviewmodel.PlayViewModel
import com.example.baisecomposelearn.model.playviewmodel.VideoInfo
import com.example.baisecomposelearn.model.playviewmodel.VideoItem
import com.example.baisecomposelearn.theme.rwGreen
import com.example.baisecomposelearn.theme.rwGreenDark

@ExperimentalCoilApi
@Composable
fun VideoCardItem(
    videoItem: VideoItem,
    isFocused: Boolean,
    onClick: () -> Unit,
    index: Int,
    viewModel: PlayViewModel?
) {
    val videoInfo = videoItem.videoInfo
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 5.dp, top = 5.dp, end = 5.dp, bottom = 5.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 8.dp,
        backgroundColor = if (isFocused) rwGreen else MaterialTheme.colors.surface
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            var width = 1280
            var height = 720
            videoInfo?.playInfo?.let {
                if (it.isNotEmpty()) {
                    width = it[0].width
                    height = it[0].height
                }
            }
            if (isFocused) {
                ExoPlayerView(isFocused, videoInfo, viewModel)
            } else {
                // 截断以下图片Url
                val coverUrl = videoInfo?.cover?.feed?.substringBefore('?')
                CoilImage(
                    url = coverUrl,
                    modifier = Modifier
                        .aspectRatio(width.toFloat() / height)
                        .fillMaxWidth()
                )
            }
            Text(
                text = "${index + 1}: ${videoInfo?.description}",
                style = MaterialTheme.typography.h6
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = videoInfo?.title ?: "",
                style = MaterialTheme.typography.body1,
                color = rwGreenDark
            )
        }
    }
}

@ExperimentalCoilApi
@Composable
fun ExoPlayerView(isFocused: Boolean, videoInfo: VideoInfo?, viewModel: PlayViewModel?) {

    val context = LocalContext.current
    // 获取播放器实例
    val exoPlayer = remember { ExoPlayerHolder.get(context = context) }
    var playerView: PlayerView? = null

    var width = 1280
    var height = 720
    videoInfo?.playInfo?.let {
        if (it.isNotEmpty()) {
            width = it[0].width
            height = it[0].height
        }
    }

    if (isFocused) {
        videoInfo?.let {
            LaunchedEffect(key1 = videoInfo.playUrl, key2 = it) {
                val playUri = Uri.parse(it.playUrl)
                val dataSourceFactory = VideoDataSourceHolder.getCacheFactory(context)
                val mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(MediaItem.fromUri(playUri))
                exoPlayer.setMediaSource(mediaSource)
                exoPlayer.prepare()
            }
        }
    }

    AndroidView(

        modifier = Modifier.aspectRatio(width.toFloat() / height),
        factory = { context ->
            val frameLayout = FrameLayout(context)
            frameLayout.setBackgroundColor(context.getColor(android.R.color.holo_purple))
            frameLayout
        },
        update = { frameLayout ->
            if (PlayerViewManager.playerViewMode == PlayViewModes.HALF_SCREEN) {
                frameLayout.removeAllViews()
                if (isFocused) {
                    playerView = PlayerViewManager.get(frameLayout.context)

                    // 切换播放器
                    PlayerView.switchTargetView(
                        exoPlayer,
                        PlayerViewManager.currentPlayerView,
                        playerView
                    )
                    PlayerViewManager.currentPlayerView = playerView

                    playerView?.apply {
                        hideController()
                        useController = false
                        player?.playWhenReady = true
                    }

                    playerView?.apply {
                        (parent as? ViewGroup)?.removeView(this)
                    }
                    frameLayout.addView(
                        playerView,
                        FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.MATCH_PARENT
                    )
                    viewModel?.saveFrameLayout(frameLayout)

                } else if (playerView != null) {
                    playerView?.apply {
                        (parent as? ViewGroup)?.removeView(this)
                        PlayerViewManager.release(this)
                    }
                    playerView = null
                }
            }
        }
    )

    DisposableEffect(key1 = videoInfo?.playUrl) {
        onDispose {

            if (isFocused) {
                playerView?.apply {
                    (parent as? ViewGroup)?.removeView(this)
                }
                exoPlayer.stop()
                playerView?.let {
                    PlayerViewManager.release(it)
                }
                playerView = null
            }
        }
    }
}


@ExperimentalCoilApi
@Composable
fun CoilImage(url: String?, modifier: Modifier) {
    Image(
        painter = rememberAsyncImagePainter(url),
        contentDescription = null,
        modifier = modifier,
    )
}