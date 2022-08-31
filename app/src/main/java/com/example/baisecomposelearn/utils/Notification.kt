package com.example.baisecomposelearn.utils

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.ResultActivity

enum class photoType {
    SmallText, LargerText, SmallPirture, LargerPirure
}

fun showNotification(context: Context, photo: photoType) {
    // CHANNEL_ID：通道ID，可在类 MainActivity 外自定义。如：val CHANNEL_ID = 'msg_1'
    val title: String = context.getString(R.string.app_name)
    val content: String = context.getString(R.string.content)
    val builder = when (photo) {
        photoType.SmallText -> {
            NotificationCompat.Builder(context, "CHANNEL_ID")
                .setSmallIcon(R.drawable.logo)
                .setContentTitle(title)
                .setContentText(content)
                // 通知优先级，可以设置为int型，范围-2至2
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        }
        photoType.LargerText -> {
            NotificationCompat.Builder(context, "CHANNEL_ID")
                .setSmallIcon(R.drawable.logo)
                .setContentTitle(title)
                .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.logo))
                .setContentText(content)
                .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.logo))
                .setStyle(NotificationCompat.BigTextStyle().bigText(content))
                // 通知优先级，可以设置为int型，范围-2至2
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        }
        photoType.SmallPirture -> {
            NotificationCompat.Builder(context, "CHANNEL_ID")
                .setSmallIcon(R.drawable.logo)
                .setContentTitle(title)
                .setContentText(content)
                .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.poster))
                // 通知优先级，可以设置为int型，范围-2至2
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        }
        photoType.LargerPirure -> {
            NotificationCompat.Builder(context, "CHANNEL_ID")
                .setSmallIcon(R.drawable.logo)
                .setContentTitle(title)
                .setContentText(content)
                .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.logo))
                .setStyle(
                    NotificationCompat.BigPictureStyle()
                        .bigPicture(
                            BitmapFactory.decodeResource(
                                context.resources,
                                R.drawable.poster
                            )
                        )
                        .bigLargeIcon(
                            BitmapFactory.decodeResource(
                                context.resources,
                                R.drawable.logo
                            )
                        )
                )
                // 通知优先级，可以设置为int型，范围-2至2
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        }
    }
    // 显示通知
    with(NotificationManagerCompat.from(context)) {
        notify(1, builder.build())
    }

}

fun setNotification(context: Context) {
    // Create an explicit intent for an Activity in your app
    val intent = Intent(context, ResultActivity::class.java)
    val pendingIntent: PendingIntent
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
        pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
    } else {
        pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_ONE_SHOT)
    }

    val builder = NotificationCompat.Builder(context, "CHANNEL_ID")
        .setSmallIcon(R.drawable.logo)
        .setContentTitle(context.getString(R.string.app_name))
        .setContentText(context.getString(R.string.content))
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        // Set the intent that will fire when the user taps the notification
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)
    with(NotificationManagerCompat.from(context)) {
        // notificationId is a unique int for each notification that you must define
        notify(2, builder.build())
    }


}