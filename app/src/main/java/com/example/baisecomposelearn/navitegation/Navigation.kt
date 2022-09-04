package com.example.baisecomposelearn.navitegation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.baisecomposelearn.screens.activate.*
import com.example.baisecomposelearn.screens.animate.*
import com.example.baisecomposelearn.screens.constraintlayout.*
import com.example.baisecomposelearn.screens.constraintlayout.ConstraintLayoutScreen
import com.example.baisecomposelearn.screens.media.*
import com.example.baisecomposelearn.screens.viewmodel.ViewModelFlowScreen
import com.example.baisecomposelearn.screens.viewmodel.ViewModelLiveDataScreen
import com.example.baisecomposelearn.screens.viewmodel.ViewModelScreen
import com.example.baisecomposelearn.screens.viewmodel.WellnessScreen


@RequiresApi(Build.VERSION_CODES.O)
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
            Media3ExoplayerScreen(navController)
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
        composable(NavitemScreen.DocumentScreen.route){
            DocumentScreen(navController)
        }
    }

}