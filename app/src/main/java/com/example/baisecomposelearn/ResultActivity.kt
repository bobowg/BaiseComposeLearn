package com.example.baisecomposelearn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.baisecomposelearn.utils.setNotification

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setNotification(this)

    }
}