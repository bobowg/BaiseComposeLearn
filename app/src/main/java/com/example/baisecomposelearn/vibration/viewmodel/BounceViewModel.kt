package com.example.baisecomposelearn.vibration.viewmodel

import android.app.Application
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.baisecomposelearn.R

/**
 * ViewModel that handles state logic for Bounce route.
 */
class BounceViewModel(
    val messageToUser: String,
) : ViewModel() {

    /**
     * Factory for BounceViewModel.
     */
    companion object {

        fun provideFactory(
            application: Application,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val vibrator = ContextCompat.getSystemService(application, Vibrator::class.java)!!

                var messageToUser = ""
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.R || !vibrator.areAllPrimitivesSupported(
                        VibrationEffect.Composition.PRIMITIVE_THUD,
                        VibrationEffect.Composition.PRIMITIVE_CLICK,
                    )
                ) {
                    messageToUser = application.getString(R.string.message_not_supported)
                }

                return BounceViewModel(
                    messageToUser = messageToUser,
                ) as T
            }
        }
    }
}
