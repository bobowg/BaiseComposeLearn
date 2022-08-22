package com.example.baisecomposelearn.navitegation

import com.example.baisecomposelearn.R

sealed class NavitemScreen(val route:String,val title:Int){
    object Activate:NavitemScreen("active", R.string.activate)
    object Animation:NavitemScreen("animation",R.string.animation)
    object Backhandler:NavitemScreen("Backhandler", R.string.backhandler)
    object Backhandlers:NavitemScreen("Backhandlers",R.string.backhandlers)
    object LanunScreen1:NavitemScreen("launch1",R.string.launch1)
    object LanunScreen2:NavitemScreen("launch2",R.string.launch2)
    object LanunScreen3:NavitemScreen("googlmap",R.string.googlemap)
    object AnimatedScreen:NavitemScreen("animatedVisibility",R.string.animatedvisibility)
    object AnimationContentSizeScreen:NavitemScreen("animationcontentsize",R.string.animationcontentsize)
    object CrossFadeScreen:NavitemScreen("crossfadescreen",R.string.crossfade)
    object UpdateTransition:NavitemScreen("updatetransition",R.string.updatetransition)
}
