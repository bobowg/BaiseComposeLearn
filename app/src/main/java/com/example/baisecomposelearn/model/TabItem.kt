package com.example.baisecomposelearn.model

import androidx.compose.runtime.Composable
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.screens.constraintlayout.BooksScreen
import com.example.baisecomposelearn.screens.constraintlayout.MoviesScreen
import com.example.baisecomposelearn.screens.constraintlayout.MusicScreen


typealias ComposeFun = @Composable () -> Unit

sealed class TabItem(var icon:Int,var title:String, var screen:ComposeFun){
    object Music:TabItem(R.drawable.ic_music,"音乐",{ MusicScreen()})
    object Movie:TabItem(R.drawable.ic_movies,"电影",{ MoviesScreen() })
    object Book:TabItem(R.drawable.ic_book,"电子书",{ BooksScreen()})
}

val tabs = listOf(
    TabItem.Music,
    TabItem.Movie,
    TabItem.Book
)