package com.example.baisecomposelearn.utils

import androidx.media3.common.Player


interface ExoEventListener {

    // 全屏半屏
    fun changeFullScreen(player: Player)

    // 退出
    fun backExitScreen(player: Player)
}