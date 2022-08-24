package com.example.baisecomposelearn.screens.constraintlayout

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.NavController
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.screens.components.ScreenModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AuthenticationScreen(navController: NavController) {
    val context = LocalContext.current
    ScreenModel(navController = navController, content = {
        Button(onClick = {
            showNotification(context)
        }
        ) {
            Text(text = "show message")
        }
    })
}


fun showNotification(context: Context) {
    // CHANNEL_ID：通道ID，可在类 MainActivity 外自定义。如：val CHANNEL_ID = 'msg_1'
    val builder = NotificationCompat.Builder(context, "CHANNEL_ID")
        .setSmallIcon(R.mipmap.ic_launcher)
        .setContentTitle("RNG赛程提醒")
        .setContentText("今天晚上19:00，RNG对阵IG")
        // 通知优先级，可以设置为int型，范围-2至2
        .setPriority(NotificationCompat.PRIORITY_MAX)
    // 显示通知
    with(NotificationManagerCompat.from(context)) {
        notify(1, builder.build())
    }
}