package com.example.baisecomposelearn.model.recordmachine

import java.io.File

interface AudioRecord {
    fun start(outputFile: File)
    fun stop()
}