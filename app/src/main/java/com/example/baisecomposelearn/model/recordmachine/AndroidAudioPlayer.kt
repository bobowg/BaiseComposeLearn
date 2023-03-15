package com.example.baisecomposelearn.model.recordmachine

import android.content.Context
import android.media.MediaPlayer
import androidx.core.net.toUri
import java.io.File

class AndroidAudioPlayer(
    private val context: Context
) : AudioPlay {
    private var player: MediaPlayer? = null

    override fun playFile(file: File) {
        MediaPlayer.create(context,file.toUri()).apply {
            player = this
            start()
        }
    }

    override fun stop() {
        player?.stop()
        player?.release()
        player = null
    }
}