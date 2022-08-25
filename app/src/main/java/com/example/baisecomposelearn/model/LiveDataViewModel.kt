package com.example.baisecomposelearn.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LiveDataViewModel: ViewModel() {
    private val _name = MutableLiveData("")
    val name:LiveData<String> = _name

    fun onNameChange(newName:String){
        _name.value = newName
    }
}