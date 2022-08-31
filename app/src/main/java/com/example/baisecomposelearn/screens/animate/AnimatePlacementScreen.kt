package com.example.baisecomposelearn.screens.animate

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.media3.extractor.MpegAudioUtil
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.screens.components.goBack

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AnimatePlacementScreen(navController: NavController) {
    val items = listOf(
        "apple",
        "banana",
        "ciwi",
        "dpple",
        "eanana",
        "fiwi",
        "gpple",
        "hanana",
        "iiwi",
        "jpple",
        "kanana",
        "liwi",
        "mpple",
        "nanana",
        "oiwi",
        "pple",
        "qanana",
        "riwi",
        "spple",
        "tanana",
        "uiwi",
        "vpple",
        "wnana",
        "hiwi",
        "ypple",
        "zanana",
        "kiwi"
    )
    var list by remember { mutableStateOf(items) }
    val route = list.groupBy { it.first().toString() }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.colorPrimary))
    ) {
        item {
            Button(
                onClick = { list = list.shuffled() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.randownmsg),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            }
        }
        route.forEach { (inital, ru) ->
            stickyHeader {
                Text(
                    text = inital,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = colorResource(id = R.color.colorPrimaryDark))
                        .padding(16.dp),
                    color = Color.White,
                    fontSize = 28.sp
                )
            }



            items(ru) { r ->
                Text(
                    text = r,
                    modifier = Modifier
                        .padding(16.dp)
                        .animateItemPlacement(tween(250)),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }


        }
        item {
            goBack(navController = navController)
        }
    }
}

@Preview
@Composable
fun AnimatePlacementScreenPreview() {
    AnimatePlacementScreen(navController = rememberNavController())
}