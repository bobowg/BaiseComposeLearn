package com.example.baisecomposelearn.navitegation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.baisecomposelearn.screens.*

@Composable
fun Navigation(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = NavitemScreen.Activate.route) {
        composable(NavitemScreen.Activate.route) {
            ActivateScreen(navController = navController)
        }
        composable(NavitemScreen.Animation.route) {
            AnimationScreen(navController = navController)
        }
        composable(NavitemScreen.Backhandlers.route) {
            BackHandlersScreen(navController = navController)
        }
        composable(NavitemScreen.Backhandler.route) {
            BackHandlerScreen(navController = navController)
        }
        composable(NavitemScreen.LanunScreen1.route) {
            LanunScreen1(navController = navController)
        }
        composable(NavitemScreen.LanunScreen2.route) {
            LanunScreen2(navController = navController)
        }
        composable(NavitemScreen.LanunScreen3.route) {
            googleMap(navController = navController)
        }
        composable(NavitemScreen.AnimatedScreen.route) {
            AnimatedScreen(navController = navController)
        }
    }

}