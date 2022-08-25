package com.example.baisecomposelearn.screens.viewmodel

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.model.FlowViewModel
import com.example.baisecomposelearn.screens.components.ScreenModel

@Composable
fun ViewModelFlowScreen(navController: NavController, viewModel: FlowViewModel = viewModel()) {
    ScreenModel(navController = navController, content = {
        val name: String by viewModel.name.collectAsState()
        HellContent(name, stringResource(id = R.string.flow)) { viewModel.onNameChange(it) }
    })
}

@Composable
internal fun HellContent(name: String, label:String, onNameChange: (String) -> Unit) {
    Text(
        text = stringResource(id = R.string.hello_with_args, name),
        modifier = Modifier.padding(bottom = 8.dp),
        style = MaterialTheme.typography.h5,
        color = Color.White
    )
    OutlinedTextField(
        value = name,
        onValueChange = { onNameChange(it) },
        label = { Text(text = label)},
        colors = TextFieldDefaults.outlinedTextFieldColors()
    )
}
