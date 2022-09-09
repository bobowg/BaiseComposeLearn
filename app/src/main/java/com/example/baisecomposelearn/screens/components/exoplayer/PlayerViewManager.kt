package com.example.baisecomposelearn.screens.components.exoplayer

import android.content.Context
import android.content.pm.ActivityInfo
import android.view.View
import androidx.core.util.Pools
import androidx.media3.common.Player
import androidx.media3.ui.PlayerView
import com.example.baisecomposelearn.MainActivity
import com.example.baisecomposelearn.model.playviewmodel.PlayViewModel
import com.example.baisecomposelearn.utils.ExoEventListener

/**
 * 用来管理 PlayerView
 * */
object PlayerViewManager : ExoEventListener {

    var currentPlayerView: PlayerView? = null

    var playerViewMode = PlayViewModes.HALF_SCREEN
    var activity: MainActivity? = null
    var viewModel: PlayViewModel? = null
    private val playerViewPool = Pools.SimplePool<PlayerView>(2)

    fun release(player: PlayerView) {
        playerViewPool.release(player)
    }
    fun get(context: Context): PlayerView {
        return PlayerView(context)
    }

    override fun changeFullScreen(player: Player) {
        switchPlayerViewMode()
    }

    override fun backExitScreen(player: Player) {
        if (isFullScreen()) {
            exitFullScreen()
        } else {
            activity?.finish()
        }
    }
    private fun switchPlayerViewMode() {
        activity = currentPlayerView?.context?.applicationContext as MainActivity
        if (activity?.requestedOrientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            //切换竖屏
            activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        } else {
            //切换横屏
            activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
    }
    private fun isFullScreen(): Boolean = playerViewMode == PlayViewModes.FULL_SCREEN

    fun exitFullScreen(): Boolean {
        if (isFullScreen()) {
            viewModel?.getRootViewGroup()?.let {

                it.removeView(currentPlayerView)
            }

            // 然后加入LazyColumn的ItemView下
            viewModel?.addPlayerViewToLazyList(currentPlayerView)

            playerViewMode = PlayViewModes.HALF_SCREEN

            return true
        }
        return false
    }
}
enum class PlayViewModes { HALF_SCREEN, FULL_SCREEN }