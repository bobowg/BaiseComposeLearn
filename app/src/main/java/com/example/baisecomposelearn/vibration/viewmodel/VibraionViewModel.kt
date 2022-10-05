package com.example.baisecomposelearn.vibration.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.HapticFeedbackConstants
import android.view.View
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ScrollState
import androidx.compose.material.ScaffoldState
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.baisecomposelearn.R
import kotlinx.coroutines.launch
import java.lang.RuntimeException

data class HomeUiState(val hapticCategories: List<HapticCategory> = emptyList())

data class HapticCategory(
    val label: String = "",
    val categoryType: HapticCategoryType,
    val buttons: List<HapticButton> = emptyList()
)

data class HapticButton(
    val label: String = "", val worksOnUserDevice: Boolean, val hapticId: Int = -1
)

enum class HapticCategoryType {
    PREDEFINED_EFFECTS, HAPTIC_FEEDBACK_CONSTANTS, COMPOSITION_PRIMITIVES
}

class VibraionViewModel(
    private val vibrator: Vibrator,
    val homeUiState: HomeUiState,
    private val scaffoldState: ScaffoldState,
    val scrollState: ScrollState
) : ViewModel() {

    /**
     * On each button click on the home page, the composable calls this event, passing along all
     * the necessary information to call the correct haptics API.
     */
    @RequiresApi(Build.VERSION_CODES.Q)
    @SuppressLint("MissingPermission")
    fun onButtonClicked(view: View, category: HapticCategoryType, effect: Int) {
        when (category) {
            HapticCategoryType.PREDEFINED_EFFECTS -> {
                if (isAndroidQOrLater) {
                    // https://developer.android.com/reference/android/os/VibrationEffect#createPredefined(int)
                    vibrator.vibrate(VibrationEffect.createPredefined(effect))
                }
            }
            HapticCategoryType.HAPTIC_FEEDBACK_CONSTANTS -> {
                // https://developer.android.com/reference/android/view/View#performHapticFeedback(int)
                view.performHapticFeedback(effect)
            }
            HapticCategoryType.COMPOSITION_PRIMITIVES -> {
                if (isAndroidROrLater) {
                    // https://developer.android.com/reference/android/os/VibrationEffect.Composition
                    vibrator.vibrate(
                        VibrationEffect.startComposition()
                            .addPrimitive(effect)
                            .compose()
                    )
                }
            }
            else -> {
                throw RuntimeException("No corresponding action for HapticCategoryType.$category.")
            }
        }
    }

    fun onSnackbarMessage(message: String) {
        viewModelScope.launch {
            scaffoldState.snackbarHostState.showSnackbar(message)
        }
    }

    /**
     * Companion object containing factory for HomeViewModel that can provide vibrator dependency
     * and construct HomeViewModelState using Application context.
     */
    companion object {
        val isAndroidQOrLater get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q // API Level 29.
        val isAndroidROrLater get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.R // API Level 30.
        val isAndroidSOrLater get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S_V2 // API Level 31.

        fun provideFactory(
            application: Application,
            scaffoldState: ScaffoldState,
            scrollState: ScrollState,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val vibrator = ContextCompat.getSystemService(application, Vibrator::class.java)!!

                // This state object is responsible for providing all the information necessary
                // for the UI to build the home page of buttons, separated into categories.
                val viewModelState = HomeUiState(
                    hapticCategories = listOf(
                        // List of predefined effects.
                        HapticCategory(
                            application.getString(R.string.home_screen_predefined_effects),
                            categoryType = HapticCategoryType.PREDEFINED_EFFECTS,
                            buttons = listOf(
                                HapticButton(
                                    label = application.getString(R.string.home_screen_tick),
                                    worksOnUserDevice = isAndroidQOrLater,
                                    hapticId = VibrationEffect.EFFECT_TICK
                                ),
                                HapticButton(
                                    application.getString(R.string.home_screen_click),
                                    worksOnUserDevice = isAndroidQOrLater,
                                    VibrationEffect.EFFECT_CLICK
                                ),
                                HapticButton(
                                    application.getString(R.string.home_screen_heavy_click),
                                    worksOnUserDevice = isAndroidQOrLater,
                                    VibrationEffect.EFFECT_HEAVY_CLICK
                                ),
                                HapticButton(
                                    application.getString(R.string.home_screen_double_click),
                                    worksOnUserDevice = isAndroidQOrLater,
                                    VibrationEffect.EFFECT_DOUBLE_CLICK
                                ),
                            )
                        ),
                        // List of haptic feedback constants.
                        HapticCategory(
                            application.getString(R.string.home_screen_haptic_feedback_constants),
                            categoryType = HapticCategoryType.HAPTIC_FEEDBACK_CONSTANTS,
                            buttons = listOf(
                                HapticButton(
                                    application.getString(R.string.home_screen_confirm),
                                    isAndroidROrLater,
                                    HapticFeedbackConstants.CONFIRM
                                ),
                                HapticButton(
                                    application.getString(R.string.home_screen_reject),
                                    isAndroidROrLater,
                                    HapticFeedbackConstants.REJECT
                                ),
                            )
                        ),
                        // List of composition primitives.
                        HapticCategory(
                            application.getString(R.string.home_screen_composition_primitives),
                            categoryType = HapticCategoryType.COMPOSITION_PRIMITIVES,
                            buttons = listOf(
                                HapticButton(
                                    application.getString(R.string.home_screen_low_tick),
                                    isAndroidSOrLater && isPrimitiveSupported(vibrator, VibrationEffect.Composition.PRIMITIVE_LOW_TICK),
                                    VibrationEffect.Composition.PRIMITIVE_LOW_TICK
                                ),
                                HapticButton(
                                    application.getString(R.string.home_screen_tick),
                                    isPrimitiveSupported(vibrator, VibrationEffect.Composition.PRIMITIVE_TICK),
                                    VibrationEffect.Composition.PRIMITIVE_TICK
                                ),
                                HapticButton(
                                    application.getString(R.string.home_screen_click),
                                    isPrimitiveSupported(vibrator, VibrationEffect.Composition.PRIMITIVE_CLICK),
                                    VibrationEffect.Composition.PRIMITIVE_CLICK
                                ),
                                HapticButton(
                                    application.getString(R.string.home_screen_slow_rise),
                                    isPrimitiveSupported(vibrator, VibrationEffect.Composition.PRIMITIVE_SLOW_RISE),
                                    VibrationEffect.Composition.PRIMITIVE_SLOW_RISE
                                ),
                                HapticButton(
                                    application.getString(R.string.home_screen_quick_rise),
                                    isPrimitiveSupported(vibrator, VibrationEffect.Composition.PRIMITIVE_QUICK_RISE),
                                    VibrationEffect.Composition.PRIMITIVE_QUICK_RISE
                                ),
                                HapticButton(
                                    application.getString(R.string.home_screen_quick_fall),
                                    isPrimitiveSupported(vibrator, VibrationEffect.Composition.PRIMITIVE_QUICK_FALL),
                                    VibrationEffect.Composition.PRIMITIVE_QUICK_FALL
                                ),
                                HapticButton(
                                    application.getString(R.string.home_screen_spin),
                                    isAndroidSOrLater && isPrimitiveSupported(vibrator, VibrationEffect.Composition.PRIMITIVE_SPIN),
                                    VibrationEffect.Composition.PRIMITIVE_SPIN
                                ),
                                HapticButton(
                                    application.getString(R.string.home_screen_thud),
                                    isAndroidSOrLater && isPrimitiveSupported(vibrator, VibrationEffect.Composition.PRIMITIVE_THUD),
                                    VibrationEffect.Composition.PRIMITIVE_THUD
                                ),

                                )
                        ),
                    )
                )
                return VibraionViewModel(
                    vibrator = vibrator, homeUiState = viewModelState,
                    scaffoldState = scaffoldState, scrollState = scrollState
                ) as T
            }
        }

        /**
         * Query whether the vibrator supports all of the given primitives.
         * If a primitive is not supported by the device, then no vibration will occur if it is played.
         */
        private fun isPrimitiveSupported(vibrator: Vibrator, primitiveId: Int): Boolean {
            if (isAndroidROrLater) return vibrator.areAllPrimitivesSupported(primitiveId)
            return false
        }
    }
}