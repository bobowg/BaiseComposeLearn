package com.example.baisecomposelearn.screens.foundation

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.model.CleanCache
import com.example.baisecomposelearn.screens.components.ScreenModel

@Composable
fun CacheDirScreen(navController: NavController) {
    val cachedir = CleanCache()
    val context = LocalContext.current
    ScreenModel(navController = navController, content = {
        Button(onClick = {
            cachedir.cleanCache(context)
            cachedir.clearData(context)
        }) {
            Text(text = stringResource(id = R.string.cachedir))
        }
    })
}

@Preview
@Composable
fun CacheDirPreviewScreen() {
    CacheDirScreen(navController = rememberNavController())
}