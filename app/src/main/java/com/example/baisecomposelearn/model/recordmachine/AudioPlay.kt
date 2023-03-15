package com.example.baisecomposelearn.model.recordmachine

import java.io.File

interface AudioPlay {
    fun playFile(file:File)
    fun stop()
}