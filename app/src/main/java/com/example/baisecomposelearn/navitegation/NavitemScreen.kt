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
    object AnimationSpecScreen:NavitemScreen("animationspec",R.string.animationspec)
    object AnimatedVectorDrawableScreen:NavitemScreen("animatedvectordrawable",R.string.animatedvector)
    object AnimatePlacementScreen:NavitemScreen("animateplacementscreen",R.string.animateitemplacement)
    object ConstraintLayoutScreen:NavitemScreen("constraintlayout",R.string.constraintlayout)
    object BarrierScreen:NavitemScreen("barrierscreen",R.string.barrier)
    object DecoupledScreen:NavitemScreen("decoupled",R.string.decoupled)
    object CircularScreen:NavitemScreen("circularscreen",R.string.circularscreen)
    object JsonConstraintScreen:NavitemScreen("jsonconstraint",R.string.jsonconstraint)
    object MotionLayoutScreen:NavitemScreen("motionlayout",R.string.motionlayout)
    object AuthenticationScreen:NavitemScreen("authentication",R.string.notication)
    object ViewModelScreen:NavitemScreen("viewmodelscreen",R.string.viewmodel)
    object WellnessScreen:NavitemScreen("wellnessscreen",R.string.wellness)
}
