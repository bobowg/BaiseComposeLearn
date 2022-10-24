package com.example.baisecomposelearn.wanandroid

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProjectService {
    @GET("project/list/{page}/json")
    suspend fun getProjects(
        @Path("page")
        page:Int,
        @Query("cid")
        id:Int
    ):ProjectResponse
}