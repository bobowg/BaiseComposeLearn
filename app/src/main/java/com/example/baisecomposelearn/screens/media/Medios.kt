package com.example.baisecomposelearn.screens.media

data class Medios(
    val id: Int,
    val getVideoUrl: String
) {
    companion object{
        val getvideoUri = listOf(
            Medios(2,"https://vfx.mtime.cn/Video/2019/03/21/mp4/190321153853126488.mp4"),
            Medios(1,"https://vfx.mtime.cn/Video/2019/02/04/mp4/190204084208765161.mp4"),
            Medios(6,"https://vfx.mtime.cn/Video/2019/03/19/mp4/190319104618910544.mp4"),
            Medios(3,"https://vfx.mtime.cn/Video/2019/03/19/mp4/190319222227698228.mp4"),
            Medios(4,"https://vfx.mtime.cn/Video/2019/03/18/mp4/190318231014076505.mp4"),
            Medios(5,"https://vfx.mtime.cn/Video/2019/03/18/mp4/190318214226685784.mp4")
        )
    }
}

data class ImagePitrue(
    val id:Int,
    val getImgUrl:String
){
    companion object {
        val getImageUrl = listOf(
            ImagePitrue(1,"https://images.pexels.com/photos/247297/pexels-photo-247297.jpeg"),
            ImagePitrue(2,"https://images.pexels.com/photos/85773/pexels-photo-85773.jpeg"),
            ImagePitrue(3,"https://images.pexels.com/photos/371589/pexels-photo-371589.jpeg"),
            ImagePitrue(4,"https://images.pexels.com/photos/815996/pexels-photo-815996.jpeg"),
            ImagePitrue(5,"https://images.pexels.com/photos/1767434/pexels-photo-1767434.jpeg"),
            ImagePitrue(6,"https://images.pexels.com/photos/245388/pexels-photo-245388.jpeg"),
            ImagePitrue(7,"https://images.pexels.com/photos/1406766/pexels-photo-1406766.jpeg")
        )
    }
}