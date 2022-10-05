package com.example.baisecomposelearn.vibration.viewmodel

import android.os.Vibrator
import androidx.compose.material.ScaffoldState
import androidx.lifecycle.ViewModel
import androidx.viewpager2.widget.ViewPager2.ScrollState

data class HomeUiState(val hapticCategories: List<HapticCategory> = emptyList())

data class HapticCategory(
    val label: String = "",
    val categoryType: HapticCategoryType,
    val buttons: List<HapticButton> = emptyList()
)

data class HapticButton(
    val label: String = "",
    val worksOnUserDevice: Boolean,
    val hapticId: Int = -1
)

enum class HapticCategoryType {
    PREDEFINED_EFFECTS, HAPTIC_FEEDBACK_CONSTANTS, COMPOSITION_PRIMITIVES
}

class VibraionViewModel(
    private val vibrator:Vibrator,
    val homeUiState: HomeUiState,
    private val scaffoldState: ScaffoldState,
    val scrollState:ScrollState
) : ViewModel() {

}