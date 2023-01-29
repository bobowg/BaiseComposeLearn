package com.example.baisecomposelearn.screens.foundation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.baisecomposelearn.screens.components.goBack
import com.example.baisecomposelearn.screens.foundation.data.PaginatorViewModell
import com.example.baisecomposelearn.theme.best
import com.example.baisecomposelearn.theme.darkTextColorPrimary

@Composable
fun Paginationcomponent(navController: NavController) {
    val viewmodel = viewModel<PaginatorViewModell>()
    val state = viewmodel.state
    LazyColumn(modifier = Modifier.fillMaxSize()){
        items(state.items.size){ i ->
            val item = state.items[i]
            Column {
                Text(text = item.title, fontFamily = best, color = darkTextColorPrimary)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = item.description, fontFamily = best, color = darkTextColorPrimary)
            }
        }
        item {
            goBack(navController = navController)
        }
    }
}