package com.example.baisecomposelearn.vibration.viewmodel

import android.app.Application
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.*
import com.example.baisecomposelearn.R

class ResistViewModel(
    val messageToUser: String,
    val isLowTickSupported: Boolean,
) : ViewModel() {
    companion object {
        fun provideFactory(
            application: Application,
        ): Factory = object : Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val vibrator = ContextCompat.getSystemService(application, Vibrator::class.java)!!
                val isTickSupperted =
                    Build.VERSION.SDK_INT >= Build.VERSION_CODES.R && vibrator.areAllPrimitivesSupported(
                        VibrationEffect.Composition.PRIMITIVE_TICK
                    )
                val isLowTickSupperted =
                    Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && vibrator.areAllPrimitivesSupported(
                        VibrationEffect.Composition.PRIMITIVE_LOW_TICK
                    )
                var messageToUser = ""
                if (!isTickSupperted && !isLowTickSupperted) {
                    messageToUser = application.getString(R.string.message_not_supported)
                } else if (!isLowTickSupperted) {
                    messageToUser = application.getString(R.string.message_degraded_experience)
                }
                return ResistViewModel(
                    messageToUser = messageToUser,
                    isLowTickSupported = isLowTickSupperted
                ) as T
            }
        }
    }
}