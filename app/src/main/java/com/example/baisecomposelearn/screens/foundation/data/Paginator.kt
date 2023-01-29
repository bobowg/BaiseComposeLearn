package com.example.baisecomposelearn.screens.foundation.data

interface Paginator<Key, Item> {
    suspend fun loadNextItems()
    fun reset()
}