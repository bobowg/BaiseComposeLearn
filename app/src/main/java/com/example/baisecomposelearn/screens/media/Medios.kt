package com.example.baisecomposelearn.screens.media

data class Medios(
    val id: Int,
    val getVideoUrl: String
) {
    companion object{
        val getvideoUri = listOf(
            Medios(2,"http://vfx.mtime.cn/Video/2019/03/21/mp4/190321153853126488.mp4"),
            Medios(1,"https://vfx.mtime.cn/Video/2019/02/04/mp4/190204084208765161.mp4"),
            Medios(6,"http://vfx.mtime.cn/Video/2019/03/19/mp4/190319104618910544.mp4"),
            Medios(3,"http://vfx.mtime.cn/Video/2019/03/19/mp4/190319222227698228.mp4"),
            Medios(4,"http://vfx.mtime.cn/Video/2019/03/18/mp4/190318231014076505.mp4"),
            Medios(5,"http://vfx.mtime.cn/Video/2019/03/18/mp4/190318214226685784.mp4")
        )
    }
}
