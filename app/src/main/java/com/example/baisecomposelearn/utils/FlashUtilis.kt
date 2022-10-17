package com.example.baisecomposelearn.utils

import android.content.Context
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager


class FlashUtils internal constructor(context: Context) {
    private var manager: CameraManager? = null
    private val context: Context
    private var status = false //记录手电筒状态

    init {
        manager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
        this.context = context
    }

    //打开手电筒
    fun open() {
        if (status) { //如果已经是打开状态，不需要打开
            return
        }
        try {
            manager!!.setTorchMode("0", true)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        status = true //记录手电筒状态为打开
    }

    //关闭手电筒
    fun close() {
        if (!status) { //如果已经是关闭状态，不需要打开
            return
        }
        try {
            manager!!.setTorchMode("0", false)
        } catch (e: CameraAccessException) {
            e.printStackTrace()
        }
        status = false //记录手电筒状态为关闭
    }

}