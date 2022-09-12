package com.example.baisecomposelearn.screens.components


data class ImagePitrue(
    val id:Int,
    val getImgUrl:String
){
    companion object {
        val getImageUrl = listOf(
            ImagePitrue(1,"https://images.pexels.com/photos/247297/pexels-photo-247297.jpeg"),
            ImagePitrue(2,"http://5b0988e595225.cdn.sohucs.com/images/20200304/7227bf9fff7e4a1fa77c044f12b3ecf6.jpeg"),
            ImagePitrue(3,"https://images.pexels.com/photos/371589/pexels-photo-371589.jpeg"),
            ImagePitrue(4,"https://images.pexels.com/photos/815996/pexels-photo-815996.jpeg"),
            ImagePitrue(5,"https://images.pexels.com/photos/1767434/pexels-photo-1767434.jpeg"),
            ImagePitrue(6,"https://images.pexels.com/photos/245388/pexels-photo-245388.jpeg"),
            ImagePitrue(7,"https://images.pexels.com/photos/1406766/pexels-photo-1406766.jpeg"),
            ImagePitrue(8,"https://pic4.zhimg.com/80/v2-50d0243ff9428b86950d4270acf3b6cf_1440w.jpg"),
            ImagePitrue(9,"https://pbs.twimg.com/media/EYBuMBoUYAARdbK.jpg"),
            ImagePitrue(10,"https://images.twgreatdaily.com/images/image/nL7/nL7G6XEBfGB4SiUwnwt6.jpg"),
            ImagePitrue(11,"https://images.twgreatdaily.com/images/image/p77/p77G6XEBfGB4SiUwqAvG.jpg"),
            ImagePitrue(12,"https://uploadfile.huiyi8.com/up/85/05/e7/8505e7a62884b3f177614bec418de259.jpg"),
        )
    }
}

internal fun imgUrl():String = ImagePitrue.getImageUrl.random().getImgUrl