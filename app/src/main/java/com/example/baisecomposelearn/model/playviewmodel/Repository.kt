package com.example.baisecomposelearn.model.playviewmodel

import com.example.baisecomposelearn.screens.components.net.RetrofitClient
import com.example.baisecomposelearn.screens.components.net.VideoListService

object Repository {
    suspend fun getVideoList(itemStart: Int, pageSize: Int) =
        RetrofitClient.createApi(VideoListService::class.java)
            .getVideoList(itemStart, pageSize)
}