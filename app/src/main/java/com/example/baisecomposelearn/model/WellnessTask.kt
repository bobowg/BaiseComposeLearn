package com.example.baisecomposelearn.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class WellnessTask(
    val id:Int,
    val lable:String,
    initialChecked:Boolean = false
) {
    var checked:Boolean by mutableStateOf(initialChecked)
}
