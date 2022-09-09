package com.example.baisecomposelearn.screens.components.net

import com.example.baisecomposelearn.model.playviewmodel.VideoStore
import retrofit2.http.GET
import retrofit2.http.Query

interface VideoListService {

    @GET("api/v4/discovery/hot")
    suspend fun getVideoList(
        @Query("start") itemStart: Int = 1,
        @Query("num") pageSize: Int = 6
    ): VideoStore
}