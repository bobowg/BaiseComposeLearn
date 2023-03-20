package com.example.baisecomposelearn.screens.media

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.model.recordmachine.AndroidAudioPlayer
import com.example.baisecomposelearn.model.recordmachine.AndroidAudioRecord
import com.example.baisecomposelearn.screens.components.ScreenModel
import java.io.File

@Composable
fun RecordScreen(navController: NavController) {
    val context = LocalContext.current.applicationContext
    val recorder by lazy {
        AndroidAudioRecord(context)
    }
    val player by lazy {
        AndroidAudioPlayer(context)
    }
    var audioFile: File? = null
    ScreenModel(
        navController = navController,
        content = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = {
                    File(context.cacheDir, "audio.mp3").also {
                        recorder.start(it)
                        audioFile = it
                    }
                }) {
                    Text(text = stringResource(id = R.string.beginrecord))
                }
                Button(onClick = {
                    recorder.stop()
                }) {
                    Text(text = stringResource(id = R.string.stoprecord))
                }
                Button(onClick = {
                    player.playFile(audioFile?:return@Button)
                }) {
                    Text(text = stringResource(id = R.string.play))
                }
                Button(onClick = {
                    player.stop()
                }) {
                    Text(text = stringResource(id = R.string.stopplay))
                }

            }

        },
        modifier = Modifier.fillMaxSize()
    )
}

@Preview
@Composable
fun RecordScreenPreivew() {
    RecordScreen(navController = rememberNavController())
}