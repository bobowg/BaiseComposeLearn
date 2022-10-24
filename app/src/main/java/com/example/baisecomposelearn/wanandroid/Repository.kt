package com.example.baisecomposelearn.wanandroid

object Repository {
    suspend fun getProjects(page:Int,id:Int) = ServiceCreator.create<ProjectService>().getProjects(page,id)
}
