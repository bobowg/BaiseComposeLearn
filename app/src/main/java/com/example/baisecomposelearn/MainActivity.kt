package com.example.baisecomposelearn

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.baisecomposelearn.theme.BaiseComposeLearnTheme
import com.example.baisecomposelearn.utils.setNotification

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createNotificationChannel()
        setContent {
            BaiseComposeLearnTheme{
                StartApp()
            }
        }
    }



    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(){
            val name = getString(R.string.app_name)
            val descriptionText = getString(R.string.content)
            // 提醒式通知(横幅显示)，不过大部分需要手动授权
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("CHANNEL_ID", name, importance).apply {description = descriptionText}
            // 注册通道(频道)
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
    }
}