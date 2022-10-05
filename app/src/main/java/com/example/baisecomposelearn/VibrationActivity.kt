package com.example.baisecomposelearn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import com.example.baisecomposelearn.screens.vibration.HapticSamplerApp

class VibrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HapticSamplerApp(application)
        }
    }
}