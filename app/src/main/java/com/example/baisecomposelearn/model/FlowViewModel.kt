package com.example.baisecomposelearn.model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FlowViewModel:ViewModel() {
    private val _name = MutableStateFlow("")
    val name:StateFlow<String> = _name
    fun onNameChange(newName:String){
        _name.value = newName
    }
}