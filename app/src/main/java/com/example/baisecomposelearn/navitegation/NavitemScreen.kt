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
    object ViewModelFlowScreen:NavitemScreen("viewmodelflowscreen",R.string.flow)
    object ViewModelLiveDataScreen:NavitemScreen("viewmodellivedatascreen",R.string.livedata)
    object MediaextensionScreen:NavitemScreen("mediaextensionscreen",R.string.mediaextension)
    object Media3ExoplayerScreen:NavitemScreen("media3exoplayerscreen",R.string.media3exoplayer)
    object CoilImageScreen:NavitemScreen("coilimagescreen",R.string.coilimage)
    object CoilLandscapistScreen:NavitemScreen("coilLandscapistscreen",R.string.coilimage)
    object QrcodeScreen:NavitemScreen("qrcodescreen",R.string.qrcode)
    object DownFontScreen:NavitemScreen("downfontscreen",R.string.font)
    object TabsScreen:NavitemScreen("tabsScreen",R.string.tabs)
    object CurtainEffectScreen:NavitemScreen("curtaineffectscreen",R.string.curtaineffect)
    object LineChartScreen:NavitemScreen("linechartscreen",R.string.linechart)
    object ComposeParticleScreen:NavitemScreen("composeparticlescreen",R.string.composeparticlescreen)
    object DocumentScreen:NavitemScreen("documentscreen",R.string.document)
    object MaterialRichTextScreen:NavitemScreen("materialrichtextscreen",R.string.materialrichtextscreen)
    object FrescoLandscapistScreen:NavitemScreen("frescolandscapistscreen",R.string.frescolandscapist)
    object FontAwesomeScreen:NavitemScreen("fontawesomescreen",R.string.fontawesomescreen)
    object RoomDatabaseScreen:NavitemScreen("roomdatabasescreen",R.string.roomdatbase)
    object GlideLandscapistScreen:NavitemScreen("glidelandscapistscreen",R.string.glidelandscapist)
    object LottieScreen:NavitemScreen("lottiescreen",R.string.lottiescreen)
    object WebViewScreen:NavitemScreen("webviewscreen",R.string.webview)
    object SwipeRefreshAccompanistScreen:NavitemScreen("swiperefreshaccompanistscreen",R.string.swiperefreshaccompanist)
    object PlotScreen:NavitemScreen("plotscreen",R.string.plotscreen)
    object AppWidgetScreen:NavitemScreen("appwidgetscreen",R.string.appwidgetscreen)
    object CustomExamplesScreen:NavitemScreen("customexamplescreen",R.string.customexamplescreen)
    object AnimatableDeleteScreen:NavitemScreen("animatabledeletescreen",R.string.animatabledeletescreen)
    object PickDateScreen:NavitemScreen("pickdatescreen",R.string.pickdatascreen)
    object CameraxScreen:NavitemScreen("cameraxscreen",R.string.camerax)
    object CircularProgressbar:NavitemScreen("circularprogressbar",R.string.animate)
    object GetJsonScreen:NavitemScreen("getjsonscreen",R.string.getjson)
    object FlashLightScreen:NavitemScreen("flashlight",R.string.flashlight)
    object AnimateColorComponent:NavitemScreen("animatecolorcomponent",R.string.animatecolorcomponent)
    object SingleValueFloatAnimationScreen:NavitemScreen("singlevaluefloatanimation",R.string.singlevaluefloatanimation)
    object ElevationAnimationScreen:NavitemScreen("elevationanimationscreen",R.string.elevationanimation)
    object BottomSheetScaffoldScreen:NavitemScreen("bottomsheetscaffoldscreen",R.string.modalbottomsheetlayout)
    object EmojiCompoatScreen:NavitemScreen("emojicompoatscreen",R.string.emojicompoat)
    object RatingBarScreen:NavitemScreen("ratingbarscreen",R.string.ratingbar)
    object CollapsingToolbar:NavitemScreen("collapsingtoolbar",R.string.collapsingtoolbar)
    object SystemUiControllerAccompanistScreen:NavitemScreen("systemuicontrollerscreen",R.string.systemuicontrolleraccompanist)
    object OrbitalExampleScreen:NavitemScreen("orbitalexample",R.string.orbitalexample)
    object CollapsingToolScreen:NavitemScreen("collapsingtoolscreen",R.string.collapsingtoolscreen)
    object CustomShapeScreen:NavitemScreen("customshapescreen",R.string.customshape)
    object WanandroidScreen:NavitemScreen("wanandroidscreen",R.string.wanandroidscreen)
    object CustomCountDownTimer:NavitemScreen("customcountdowntimer",R.string.customcountdowntimer)
    object BannerAd:NavitemScreen("banner",R.string.banner)
    object ZoomAbleComposeImage:NavitemScreen("zoomableComposeimage",R.string.zoomablecomposeimage)
    object CustomCalendarView:NavitemScreen("customcalendarview",R.string.customcalendarview)
    object RotateAnimationScreen:NavitemScreen("rotateanimationscreen",R.string.rotateanimation)
}
