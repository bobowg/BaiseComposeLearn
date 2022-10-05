package com.example.baisecomposelearn.screens.vibration

import android.app.Application
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.navitegation.HapticSamplerDestinations
import com.example.baisecomposelearn.navitegation.HapticSamplerNavGraph
import com.example.baisecomposelearn.navitegation.HapticSamplerNavigation
import com.example.baisecomposelearn.theme.DrawerShape
import com.example.baisecomposelearn.theme.HapticSamplerTheme
import com.example.baisecomposelearn.vibration.appdraw.AppDrawer
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

@Composable
fun HapticSamplerApp(application: Application) {
    HapticSamplerTheme {
        val navController = rememberNavController()
        val navigationActions = remember(navController) {
            HapticSamplerNavigation(navController)
        }
        val scrollState = rememberScrollState()
        val coroutineScope = rememberCoroutineScope()
        val systemUiController = rememberSystemUiController()

        val isScrolled = scrollState.value > 0
        val systemAndTopBarColor =
            if (isScrolled) MaterialTheme.colors.onBackground else MaterialTheme.colors.background

        SideEffect {
            systemUiController.setSystemBarsColor(systemAndTopBarColor)
        }

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute =
            navBackStackEntry?.destination?.route ?: HapticSamplerDestinations.HOME_ROUTE
        val snackbarHostState = remember { SnackbarHostState() }
        val scaffoldState = rememberScaffoldState(snackbarHostState = snackbarHostState)

        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopAppBar(
                    title = {},
                    elevation = animateDpAsState(if (isScrolled) 4.dp else 0.dp).value,
                    backgroundColor = systemAndTopBarColor,
                    navigationIcon = {
                        IconButton(onClick = {
                            coroutineScope.launch {
                                toggleDrawer(scaffoldState)
                            }
                        }) {
                            Icon(
                                Icons.Filled.Menu,
                                contentDescription = stringResource(R.string.drawer_content_description)
                            )
                        }
                    }
                )
            },
            drawerShape = DrawerShape,
            drawerContent = {
                AppDrawer(
                    currentRoute = currentRoute,
                    navigateToHome = navigationActions.navigateToHome,
                    navigateToResist = navigationActions.navigateToResist,
                    navigateToExpand = navigationActions.navigateToExpand,
                    navigateToBounce = navigationActions.navigateToBounce,
                    navigateToWobble = navigationActions.navigateToWobble,
                    closeDrawer = {
                        coroutineScope.launch {
                            toggleDrawer(scaffoldState)
                        }
                    },
                )
            }
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                HapticSamplerNavGraph(
                    application = application,
                    navController = navController,
                    scaffoldState = scaffoldState,
                    scrollState = scrollState,
                )
            }
        }
    }
}

private suspend fun toggleDrawer(scaffoldState: ScaffoldState) {
    scaffoldState.drawerState.apply {
        if (isClosed) open() else close()
    }
}