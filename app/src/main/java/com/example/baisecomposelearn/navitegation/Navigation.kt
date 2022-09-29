package com.example.baisecomposelearn.navitegation

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.baisecomposelearn.ResultActivity
import com.example.baisecomposelearn.screens.activate.*
import com.example.baisecomposelearn.screens.animate.*
import com.example.baisecomposelearn.screens.components.camerax.Route
import com.example.baisecomposelearn.screens.components.camerax.Route.VIDEO_PREVIEW_ARG
import com.example.baisecomposelearn.screens.components.camerax.VideoPreviewScreen
import com.example.baisecomposelearn.screens.constraintlayout.*
import com.example.baisecomposelearn.screens.customexamples.*
import com.example.baisecomposelearn.screens.media.*
import com.example.baisecomposelearn.screens.viewmodel.ViewModelFlowScreen
import com.example.baisecomposelearn.screens.viewmodel.ViewModelLiveDataScreen
import com.example.baisecomposelearn.screens.viewmodel.ViewModelScreen
import com.example.baisecomposelearn.screens.viewmodel.WellnessScreen
import com.example.baisecomposelearn.screens.widget.AppWidgetScreen

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun Navigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = NavitemScreen.Activate.route
    ) {
        composable(NavitemScreen.Activate.route) {
            ActivateScreen(navController)
        }
        composable(NavitemScreen.Animation.route) {
            AnimationScreen(navController)
        }
        composable(NavitemScreen.Backhandlers.route) {
            BackHandlersScreen(navController)
        }
        composable(NavitemScreen.Backhandler.route) {
            BackHandlerScreen(navController)
        }
        composable(NavitemScreen.LanunScreen1.route) {
            LanunScreen1(navController)
        }
        composable(NavitemScreen.LanunScreen2.route) {
            LanunScreen2(navController)
        }
        composable(NavitemScreen.LanunScreen3.route) {
            googleMap(navController)
        }
        composable(NavitemScreen.AnimatedScreen.route) {
            AnimatedScreen(navController)
        }
        composable(NavitemScreen.AnimationContentSizeScreen.route) {
            AnimationContentSizeScreen(navController)
        }
        composable(NavitemScreen.CrossFadeScreen.route){
            CrossFadeScreen(navController)
        }
        composable(NavitemScreen.UpdateTransition.route){
            UpdateTransition(navController)
        }
        composable(NavitemScreen.AnimationSpecScreen.route){
            AnimationSpecScreen(navController)
        }
        composable(NavitemScreen.AnimatedVectorDrawableScreen.route){
            AnimatedVectorDrawableScreen(navController)
        }
        composable(NavitemScreen.AnimatePlacementScreen.route){
            AnimatePlacementScreen(navController)
        }
        composable(NavitemScreen.ConstraintLayoutScreen.route){
            ConstraintLayoutScreen(navController)
        }
        composable(NavitemScreen.BarrierScreen.route){
            BrrierScreen(navController)
        }
        composable(NavitemScreen.DecoupledScreen.route){
            DecoupledScreen(navController)
        }
        composable(NavitemScreen.CircularScreen.route){
            CircularScreen(navController)
        }
        composable(NavitemScreen.JsonConstraintScreen.route){
            JsonConstraintScreen(navController)
        }
        composable(NavitemScreen.MotionLayoutScreen.route){
            MotionLayoutScreen(navController)
        }
        composable(NavitemScreen.AuthenticationScreen.route){
            AuthenticationScreen(navController)
        }
        composable(NavitemScreen.ViewModelScreen.route){
            ViewModelScreen(navController)
        }
        composable(NavitemScreen.WellnessScreen.route){
            WellnessScreen(navController)
        }
        composable(NavitemScreen.ViewModelFlowScreen.route){
            ViewModelFlowScreen(navController)
        }
        composable(NavitemScreen.ViewModelLiveDataScreen.route){
            ViewModelLiveDataScreen(navController)
        }
        composable(NavitemScreen.MediaextensionScreen.route){
            MediaextensionScreen(navController)
        }
        composable(NavitemScreen.Media3ExoplayerScreen.route){
            Media3ExoplayerScreen()
        }
        composable(NavitemScreen.CoilImageScreen.route){
            CoilImageScreen(navController)
        }
        composable(NavitemScreen.CoilLandscapistScreen.route){
            CoilLandscapistScreen(navController)
        }
        composable(NavitemScreen.QrcodeScreen.route){
            QrcodeScreen(navController)
        }
        composable(NavitemScreen.DownFontScreen.route){
            DownFontScreen(navController)
        }
        composable(NavitemScreen.TabsScreen.route){
            TabsScreen(navController)
        }
        composable(NavitemScreen.CurtainEffectScreen.route){
            CurtainEffectScreen(navController)
        }
        composable(NavitemScreen.LineChartScreen.route){
            LineChartScreen(navController)
        }
        composable(NavitemScreen.ComposeParticleScreen.route){
            ComposeParticleScreen(navController)
        }
        composable(NavitemScreen.DocumentScreen.route) {
            DocumentScreen(navController)
        }
        composable(NavitemScreen.MaterialRichTextScreen.route) {
            MaterialRichTextScreen(navController)
        }
        composable(NavitemScreen.FrescoLandscapistScreen.route) {
            FrescoLandscapistScreen(navController)
        }
        composable(NavitemScreen.FontAwesomeScreen.route) {
            FontAwesomeScreen(navController)
        }
        composable(NavitemScreen.RoomDatabaseScreen.route) {
            val context = LocalContext.current
            val sendIntent = Intent(context, ResultActivity::class.java)
            startActivity(context,sendIntent, Bundle.EMPTY)
        }
        composable(NavitemScreen.GoogleMapsScreen.route){
            GoogleMapsScreen()
        }
        composable(NavitemScreen.GlideLandscapistScreen.route){
            GlideLandscapistScreen(navController)
        }
        composable(NavitemScreen.LottieScreen.route){
            LottieScreen(navController)
        }
        composable(NavitemScreen.WebViewScreen.route){
            WebViewScreen(navController)
        }
        composable(NavitemScreen.SwipeRefreshAccompanistScreen.route){
            SwipeRefreshAccompanistScreen(navController)
        }
        composable(NavitemScreen.PlotScreen.route){
            PlotScreen(navController)
        }
        composable(NavitemScreen.AppWidgetScreen.route){
            AppWidgetScreen()
        }
        composable(NavitemScreen.CustomExamplesScreen.route){
            CustomExamplesScreen(navController)
        }
        composable(NavitemScreen.AnimatableDeleteScreen.route){
            AnimatableDeleteScreen(navController)
        }
        composable(NavitemScreen.PickDateScreen.route){
            PickDateScreen(navController)
        }
        composable(NavitemScreen.AdMobBannerScreen.route){
            AdMobBannerScreen()
        }
        composable(NavitemScreen.CameraxScreen.route){
            CameraxScreen(navController)
        }
        composable(Route.VIDEO_PREVIEW_FULL_ROUTE) {
            val uri = it.arguments?.getString(VIDEO_PREVIEW_ARG) ?: ""
            VideoPreviewScreen(uri = uri)
        }
        composable(NavitemScreen.CircularProgressbar.route){
            CircularProgressbar(navController)
        }
        composable(NavitemScreen.GetJsonScreen.route){
            GetJsonScreen(navController)
        }
        composable(NavitemScreen.FlashLightScreen.route){
            FlashLightScreen(navController)
        }
    }

}