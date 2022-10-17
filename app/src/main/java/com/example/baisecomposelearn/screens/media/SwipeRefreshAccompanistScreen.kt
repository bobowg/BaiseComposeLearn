package com.example.baisecomposelearn.screens.media

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.screens.components.goBack
import com.example.baisecomposelearn.screens.components.randomSampleImageUrl
import com.example.baisecomposelearn.theme.rwGreen
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.delay

@Composable
fun SwipeRefreshAccompanistScreen(navController: NavController) {
    var refreshing by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = refreshing) {
        if (refreshing) {
            delay(2000)
            refreshing = false
        }
    }
        SwipeRefresh(
            modifier = Modifier.background(rwGreen),
            state = rememberSwipeRefreshState(isRefreshing = refreshing),
            onRefresh = { refreshing = true },
            indicator = { state, trigger ->
                SwipeRefreshIndicator(
                    state = state,
                    refreshTriggerDistance = trigger,
                    scale = true,
                    arrowEnabled = true,
                    backgroundColor = rwGreen,
                    shape = MaterialTheme.shapes.small,
                    largeIndication = true,
                    elevation = 16.dp
                )
            }
        ) {
            LazyColumn {
                items(30) { index ->
                    Row(Modifier.padding(16.dp)) {
                        Image(
                            painter = rememberAsyncImagePainter(model = randomSampleImageUrl()),
                            contentDescription = null,
                            modifier = Modifier.size(64.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = stringResource(id = R.string.backhandler_count, index + 1),
                            style = MaterialTheme.typography.subtitle2,
                            modifier = Modifier
                                .weight(1f)
                                .align(Alignment.CenterVertically)
                        )
                    }
                }
                item{
                    goBack(navController = navController)
                }
            }

        }

}