package com.example.baisecomposelearn.screens.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.model.LiveDataViewModel
import com.example.baisecomposelearn.screens.components.ScreenModel

@Composable
fun ViewModelLiveDataScreen(navController: NavController) {
    ScreenModel(navController = navController, content = {
        LiveDataExplame()
    })
}

@Composable
fun LiveDataExplame(viewModel: LiveDataViewModel = viewModel()) {
    val name by viewModel.name.observeAsState("")
    HellContent(name = name, label = stringResource(id = R.string.livedata), onNameChange = { viewModel.onNameChange(it) })
}

@Preview
@Composable
fun ViewModelLiveDataScreenPreview() {
    ViewModelLiveDataScreen(navController = rememberNavController())
}