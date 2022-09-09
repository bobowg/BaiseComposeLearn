package com.example.baisecomposelearn.screens.components.exoplayer

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.VideoSize
import androidx.media3.exoplayer.DefaultLoadControl
import androidx.media3.exoplayer.ExoPlayer

object ExoPlayerHolder {
    private var exoPlayer: ExoPlayer? = null

    fun get(context: Context): ExoPlayer {
        if (exoPlayer == null) {
            exoPlayer = createExoPlayer(context)
        }
        exoPlayer!!.addListener(object : Player.Listener {
            override fun onPlayerError(error: PlaybackException) {
                super.onPlayerError(error)
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }

            override fun onVideoSizeChanged(videoSize: VideoSize) {
                super.onVideoSizeChanged(videoSize)
                Log.d(
                    "--PlayerDemo",
                    "onVideoSizeChanged: ${videoSize.width} x ${videoSize.height} | ratio: ${videoSize.pixelWidthHeightRatio}"
                )
            }
        })
        return exoPlayer!!
    }

    private fun createExoPlayer(context: Context): ExoPlayer {
        return ExoPlayer.Builder(context).setLoadControl(
            DefaultLoadControl.Builder().setBufferDurationsMs(
                DefaultLoadControl.DEFAULT_MIN_BUFFER_MS,
                DefaultLoadControl.DEFAULT_MAX_BUFFER_MS,
                DefaultLoadControl.DEFAULT_BUFFER_FOR_PLAYBACK_MS / 10,
                DefaultLoadControl.DEFAULT_BUFFER_FOR_PLAYBACK_AFTER_REBUFFER_MS / 10
            ).build()
        ).build()
            .apply {
                // 播放模式，设置为不重复播放
                repeatMode = Player.REPEAT_MODE_ONE
            }
    }
}