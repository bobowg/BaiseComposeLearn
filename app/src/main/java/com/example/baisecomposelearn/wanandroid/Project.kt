package com.example.baisecomposelearn.wanandroid


data class ProjectResponse(
    val data: Data
)

data class Data(
    val curPage: Int,
    val datas: List<Project>,
    val pageCount: Int
)

data class Project(
    val author: String,
    val envelopePic: String,
    val title: String,
    val niceDate: String,
    val desc: String,
    val link: String,
    val projectLink: String,
)