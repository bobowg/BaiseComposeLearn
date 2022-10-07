package com.example.baisecomposelearn.navitegation

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ScrollState
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.screens.customexamples.CustomExamplesScreen
import com.example.baisecomposelearn.screens.vibration.bounce.BounceRoute
import com.example.baisecomposelearn.screens.vibration.expand.ExpandRoute
import com.example.baisecomposelearn.screens.vibration.home.HomeRoute
import com.example.baisecomposelearn.screens.vibration.resist.ResistRoute
import com.example.baisecomposelearn.vibration.viewmodel.BounceViewModel
import com.example.baisecomposelearn.vibration.viewmodel.ExpandViewModel
import com.example.baisecomposelearn.vibration.viewmodel.ResistViewModel
import com.example.baisecomposelearn.vibration.viewmodel.VibraionViewModel

object HapticSamplerDestinations {
    const val HOME_ROUTE = "首页"
    const val RESIST_ROUTE = "抵抗"
    const val EXPAND_ROUTE = "扩张"
    const val BOUNCE_ROUTE = "弹跳"
    const val WOBBLE_ROUTE = "退出"
}

class HapticSamplerNavigation(navController: NavHostController) {
    val navigateToHome: () -> Unit = {
        navController.navigate(HapticSamplerDestinations.HOME_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    val navigateToResist: () -> Unit = {
        navController.navigate(HapticSamplerDestinations.RESIST_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    val navigateToExpand: () -> Unit = {
        navController.navigate(HapticSamplerDestinations.EXPAND_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    val navigateToBounce: () -> Unit = {
        navController.navigate(HapticSamplerDestinations.BOUNCE_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    val navigateToWobble: () -> Unit = {
        navController.navigate(HapticSamplerDestinations.WOBBLE_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun HapticSamplerNavGraph(
    application: Application,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = HapticSamplerDestinations.HOME_ROUTE,
    scaffoldState: ScaffoldState,
    scrollState: ScrollState,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(HapticSamplerDestinations.HOME_ROUTE) {
            val vibraionViewModel: VibraionViewModel = viewModel(
                factory = VibraionViewModel.provideFactory(application, scaffoldState, scrollState)
            )
            HomeRoute(vibraionViewModel)
        }
        composable(HapticSamplerDestinations.RESIST_ROUTE) {
            val resistViewModel: ResistViewModel = viewModel(
                factory = ResistViewModel.provideFactory(application)
            )
            ResistRoute(resistViewModel)
        }
        composable(HapticSamplerDestinations.EXPAND_ROUTE) {
            val expandViewModel: ExpandViewModel = viewModel(
                factory = ExpandViewModel.provideFactory(application)
            )
            ExpandRoute(expandViewModel)
        }
        composable(HapticSamplerDestinations.BOUNCE_ROUTE) {
            val bounceViewModel: BounceViewModel =
                viewModel(factory = BounceViewModel.provideFactory(application))
            BounceRoute(viewModel = bounceViewModel)
        }
        composable(HapticSamplerDestinations.WOBBLE_ROUTE){
            CustomExamplesScreen(navController)
        }

    }
}