package com.example.baisecomposelearn

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import com.example.baisecomposelearn.theme.BaiseComposeLearnTheme
import com.google.firebase.analytics.FirebaseAnalytics


class MainActivity : ComponentActivity() {

    private var mFirebaseAnalytics: FirebaseAnalytics? = null
    @SuppressLint("MissingPermission")
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        createNotificationChannel()
        setContent {
            BaiseComposeLearnTheme {
                StartApp()
            }
        }
        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
    }

    private fun createNotificationChannel() {
        val name = getString(R.string.app_name)
        val descriptionText = getString(R.string.content)
        // 提醒式通知(横幅显示)，不过大部分需要手动授权
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel("CHANNEL_ID", name, importance).apply {
                description = descriptionText
            }
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        // 注册通道(频道)
        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}