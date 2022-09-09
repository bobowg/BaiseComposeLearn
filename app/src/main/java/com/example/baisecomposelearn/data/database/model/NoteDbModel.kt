package com.example.baisecomposelearn.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteDbModel(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "content") val content: String,
    @ColumnInfo(name = "in_trash") val isInTrash: Boolean,
) {
    companion object {
        val DEFAULT_NOTES = listOf(
            NoteDbModel(1, "潤出","從25日那天，潤出天朝，經曼谷，馬尼拉，到到今天成功入境美國，一切出奇的順利。值得稱讚的是舊金山海關效率極高順利的蓋了i51的入境章，接下來就等綠卡了，美帝新生活，從今天開啟",true),
            NoteDbModel(2,"不要试图","不要试图用网络猛男照和毒鸡汤迷惑砂轮！哼，不吃这一套！发5则垃圾推，操作了千多个僵尸粉就随便冒充成功人士吗",false),
            NoteDbModel(3,"中国历史","中国历史就是一部不断亡国的历史",false)
        )
    }
}
