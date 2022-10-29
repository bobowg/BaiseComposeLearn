package com.example.baisecomposelearn.service

import android.app.Service
import android.content.Intent
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.os.IBinder

class RingtonePlayingService : Service() {
    private lateinit var ringtone: Ringtone
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val ringtoneUri = Uri.parse(intent.extras!!.getString("ringtone-uri"))
        ringtone = RingtoneManager.getRingtone(this, ringtoneUri)
        ringtone.play()
        return START_NOT_STICKY
    }


    override fun onDestroy() {
        ringtone.stop()
    }

}