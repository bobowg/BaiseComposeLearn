package com.example.baisecomposelearn.screens.foundation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.screens.components.goBack
import com.example.baisecomposelearn.theme.rwGreen

@Composable
fun GuidetoLazy(navController: NavController) {
    val state = rememberLazyGridState(
        initialFirstVisibleItemIndex = 100
    )
    LazyVerticalGrid(
        columns = GridCells.Adaptive(100.dp),
        state = state,
        content = {
            items(100) { i ->
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(5.dp))
                        .background(rwGreen),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "项目 $i")
                }
            }
            item{
                goBack(navController = navController)
            }
        }
    )

}

@Preview
@Composable
fun GuidetoLazyPreview() {
    GuidetoLazy(navController = rememberNavController())
}