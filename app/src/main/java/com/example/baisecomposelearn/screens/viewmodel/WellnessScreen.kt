package com.example.baisecomposelearn.screens.viewmodel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.appdrawer.DefaultTopAppBar
import com.example.baisecomposelearn.model.WellnessViewModel

@Composable
fun WellnessScreen(navController: NavController) {
    val wellnessViewModel: WellnessViewModel = viewModel()
    Scaffold(
        topBar = {
            DefaultTopAppBar(
                navController = navController,
                title = stringResource(id = R.string.app_name)
            )
        },
        content = { paddingValues ->
            WellnessTasksList(
                list = wellnessViewModel.tasks,
                onCheckedTask = { task, checked ->
                    wellnessViewModel.changeTaskChecked(task, checked)
                },
                onCloseTask = { task ->
                    wellnessViewModel.remove(task)
                },
                modifier = Modifier.padding(top = 72.dp)
            )
            StatefulCounter(
                modifier = Modifier
                    .padding(paddingValues)
            )
        }
    )

}


@Composable
fun StatefulCounter(modifier: Modifier = Modifier) {
    var count by rememberSaveable { mutableStateOf(0) }
    StatelessCounter(count = count, onInCrement = { count++ }, modifier = modifier)

}

@Composable
fun StatelessCounter(count: Int, onInCrement: () -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        if (count > 0) {
            Text(text = stringResource(id = R.string.wellnessglass, count))
        }
        Button(
            onClick = onInCrement,
            enabled = count < 10,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Text(text = stringResource(id = R.string.add), fontSize = 24.sp)
        }
    }
}

@Preview
@Composable
fun WellnessScreenPreview() {
    WellnessScreen(navController = rememberNavController())
}