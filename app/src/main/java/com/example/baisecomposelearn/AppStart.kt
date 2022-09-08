package com.example.baisecomposelearn

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import dagger.hilt.android.HiltAndroidApp
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.appdrawer.Drawer
import com.example.baisecomposelearn.appdrawer.TopAppBar
import com.example.baisecomposelearn.navitegation.Navigation



@HiltAndroidApp
class AppStart :Application(){

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun StartApp() {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(initialValue = DrawerValue.Closed))
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(scope = scope, scoffoldState = scaffoldState)
        },
        drawerBackgroundColor = colorResource(id = R.color.colorPrimary),
        drawerContent = {
            Drawer(scope = scope, scoffoldState = scaffoldState, navController = navController)
        },
        contentColor = colorResource(id = R.color.colorPrimary),
        content = {padding ->
            Surface(modifier = Modifier.padding(padding)){
                Navigation(navController = navController)
            }
        }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun StartAppPreview() {
    StartApp()
}