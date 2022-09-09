package com.example.baisecomposelearn.model.playviewmodel

import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.ui.PlayerView
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.baisecomposelearn.screens.components.net.VideoListDataSource


class PlayViewModel() : ViewModel() {
    private var frameLayout: FrameLayout? = null
    private var contentRootView:ViewGroup? = null

    val videoItemList = Pager(
        config = PagingConfig(
            pageSize = 4,
            initialLoadSize = 8,
            prefetchDistance = 2,
        )
    ){
        VideoListDataSource(Repository)
    }.flow.cachedIn(viewModelScope)

    fun saveFrameLayout(frameLayout: FrameLayout){
        this.frameLayout = frameLayout
    }

    fun addPlayerViewToLazyList(playerView:PlayerView?){
        frameLayout?.addView(
            playerView,
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        )
    }
    fun settRootViewGroup(rootView: ViewGroup){
        contentRootView = rootView
    }
    fun getRootViewGroup():ViewGroup? = contentRootView
}