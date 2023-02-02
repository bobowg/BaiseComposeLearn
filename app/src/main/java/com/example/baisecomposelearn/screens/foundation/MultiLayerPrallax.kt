package com.example.baisecomposelearn.screens.foundation

import android.content.res.Resources
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.theme.*
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.screens.components.goBack

@Composable
fun MultiLayerPrallax(navController: NavController) {
    val moonScrollaxSpeed = 0.08f
    val midBgScrollSpeed = 0.03f
    val imageHeight = (LocalConfiguration.current.screenWidthDp * (2f / 3f)).dp
    val lazyListState = rememberLazyListState()
    var moonoffset by remember {
        mutableStateOf(0f)
    }
    var midBgffset by remember {
        mutableStateOf(0f)
    }
    val nestedScrollConnection = object : NestedScrollConnection {
        override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
            val delta = available.y
            val layoutInfo = lazyListState.layoutInfo
            if (lazyListState.firstVisibleItemIndex == 0){
                return Offset.Zero
            }
            if (layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount -1){
                return Offset.Zero
            }
            moonoffset += delta * moonScrollaxSpeed
            midBgffset += delta * midBgScrollSpeed
            return Offset.Zero
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .nestedScroll(nestedScrollConnection),
        state = lazyListState
    ) {
        items(count = 10) {
            Text(
                text = "简单项目",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                fontFamily = best,
                fontSize = 20.sp,
                color = rwGreen
            )
        }
        item {
            Box(
                modifier = Modifier
                    .clipToBounds()
                    .fillMaxWidth()
                    .height(imageHeight + midBgffset.toDp())
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                rwGreen, rwGreenDark,
                                rwRed, rwOrgen
                            )
                        )
                    )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_moonbg),
                    contentDescription = "moon",
                    contentScale = ContentScale.FillWidth,
                    alignment = Alignment.BottomCenter,
                    modifier = Modifier
                        .matchParentSize()
                        .graphicsLayer {
                            translationY = moonoffset
                        }
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_midbg),
                    contentDescription = "mid bg",
                    contentScale = ContentScale.FillWidth,
                    alignment = Alignment.BottomCenter,
                    modifier = Modifier
                        .matchParentSize()
                        .graphicsLayer {
                            translationY = midBgffset
                        }
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_outerbg),
                    contentDescription = "out bg",
                    contentScale = ContentScale.FillWidth,
                    alignment = Alignment.BottomCenter,
                    modifier = Modifier.matchParentSize()
                )

            }
        }
        items(count = 20) {
            Text(
                text = "简单项目",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                fontFamily = best,
                fontSize = 20.sp,
                color = rwGreen
            )
        }
        item {
            goBack(navController = navController)
        }
    }
}

private fun Float.toDp():Dp{
    return (this / Resources.getSystem().displayMetrics.density).dp
}

@Preview(showBackground = true)
@Composable
fun MultiLayerPreview() {
    MultiLayerPrallax(navController = rememberNavController())
}