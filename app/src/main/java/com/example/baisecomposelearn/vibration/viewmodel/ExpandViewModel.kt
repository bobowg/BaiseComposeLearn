package com.example.baisecomposelearn.vibration.viewmodel

import android.app.Application
import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.baisecomposelearn.R

/**
 * ViewModel that handles state logic for Expand route.
 */
class ExpandViewModel(
    val messageToUser: String,
) : ViewModel() {

    /**
     * Factory for ExpandViewModel.
     */
    companion object {

        fun provideFactory(
            application: Application,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val vibrator = application.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

                var messageToUser = ""
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R || !vibrator.areAllPrimitivesSupported(
                        VibrationEffect.Composition.PRIMITIVE_SLOW_RISE,
                        VibrationEffect.Composition.PRIMITIVE_QUICK_FALL,
                        VibrationEffect.Composition.PRIMITIVE_TICK
                    )
                ) {
                    messageToUser = application.getString(R.string.message_not_supported)
                }

                return ExpandViewModel(
                    messageToUser = messageToUser,
                ) as T
            }
        }
    }
}
