package com.example.baisecomposelearn.wanandroid

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn

class WanandroidViewModel:ViewModel() {
    val projects = Pager(PagingConfig(pageSize = 20)){
        ProjectPagingSource(Repository)
    }.flow.cachedIn(viewModelScope)
}