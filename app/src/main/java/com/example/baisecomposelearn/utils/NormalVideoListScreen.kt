package com.example.baisecomposelearn.utils

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import coil.annotation.ExperimentalCoilApi
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.model.playviewmodel.PlayViewModel
import com.example.baisecomposelearn.model.playviewmodel.VideoItem
import com.example.baisecomposelearn.screens.components.exoplayer.VideoCardItem
import com.example.baisecomposelearn.theme.rwGreen
import com.example.baisecomposelearn.theme.rwGreenDark
import com.example.baisecomposelearn.theme.rwRed
import kotlinx.coroutines.launch
import timber.log.Timber


/**
 * 首页列表加载 ---普通加载，没有下拉刷新，可加载下一页
 * */

@OptIn(ExperimentalCoilApi::class)
@Composable
fun NormalVideoListScreen(
    viewModel: PlayViewModel,
    context: Context,
) {

    val collectAsLazyPagingIDataList = viewModel.videoItemList.collectAsLazyPagingItems()

    // 首次加载业务逻辑
    when (collectAsLazyPagingIDataList.loadState.refresh) {
        is LoadState.NotLoading -> {
            ContentInfoList(
                collectAsLazyPagingIDataList = collectAsLazyPagingIDataList,
                context = context,
                viewModel = viewModel
            )
        }
        is LoadState.Error -> ErrorPage() { collectAsLazyPagingIDataList.refresh() }
        is LoadState.Loading -> LoadingPageUI()
    }
}

@SuppressLint("UnrememberedMutableState")
@ExperimentalCoilApi
@Composable
fun ContentInfoList(
    context: Context,
    collectAsLazyPagingIDataList: LazyPagingItems<VideoItem>,
    viewModel: PlayViewModel
) {
    val lazyListState = rememberLazyListState()
    val focusIndex by derivedStateOf { lazyListState.firstVisibleItemIndex }
    val coroutineScope = rememberCoroutineScope()

    LazyColumn(
        state = lazyListState
    ) {
        itemsIndexed(collectAsLazyPagingIDataList) { index, videoItem ->
            // 传入列表卡片Item
            VideoCardItem(
                videoItem = videoItem!!,
                isFocused = index == focusIndex,
                onClick = { Toast.makeText(context, "ccc", Toast.LENGTH_SHORT).show() },
                index = index,
                viewModel = viewModel
            )
        }

        // 加载下一页业务逻辑
        val appendState = collectAsLazyPagingIDataList.loadState.append
        Timber.tag("xx---").d(" appendState :%s", appendState)

        when (appendState) {
            is LoadState.NotLoading -> {
                itemsIndexed(collectAsLazyPagingIDataList) { index, videoItem ->
                    VideoCardItem(
                        videoItem = videoItem!!,
                        isFocused = index == focusIndex,
                        onClick = { Toast.makeText(context, "ccc", Toast.LENGTH_SHORT).show() },
                        index = index,
                        viewModel = viewModel
                    )
                }
            }
            is LoadState.Error -> item {
                NextPageLoadError {
                    collectAsLazyPagingIDataList.retry()
                }
            }

            LoadState.Loading -> item {
                LoadingPageUI()
            }
        }

        if (appendState == LoadState.NotLoading(endOfPaginationReached = true)) {
            item {
                // 没有更多了
                NotMoreDataFindUI(onClick = {
                    // 回滚到顶部
                    coroutineScope.launch {
                        lazyListState.animateScrollToItem(0)
                    }
                })
            }
        }
    }
}


/**
 * 页面加载失败重试
 * */
@Composable
fun ErrorPage(onclick: () -> Unit = {}) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(219.dp, 119.dp),
            painter = painterResource(id = R.drawable.ic_default_empty),
            contentDescription = "网络问题",
            contentScale = ContentScale.Crop
        )
        Button(
            modifier = Modifier.padding(8.dp),
            onClick = onclick,
        ) {
            Text(text = "网络不佳，请点击重试")
        }
    }
}

/**
 * 加载中动效
 * */
@Composable
fun LoadingPageUI() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(170.dp), contentAlignment = Alignment.Center
    ) {
        val animator by rememberInfiniteTransition().animateFloat(
            initialValue = 0f,
            targetValue = 360f,
            animationSpec = infiniteRepeatable(
                tween(800, easing = LinearEasing),
                repeatMode = RepeatMode.Restart
            )
        )
        Canvas(modifier = Modifier.fillMaxSize()) {
            translate(80f, 80f) {
                drawArc(
                    color = rwRed,
                    startAngle = 0f,
                    sweepAngle = animator,
                    useCenter = false,
                    size = Size(80 * 2f, 80 * 2f),
                    style = Stroke(12f),
                    alpha = 0.6f,
                )
            }
        }
    }
}

/**
 * 加载下一页失败
 * */
@Composable
fun NextPageLoadError(onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Button(onClick = onClick) {
            Text(text = "重试")
        }
    }
}

/**
 * 没有更多了
 * */
@Composable
fun NotMoreDataFindUI(onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        TextButton(
            onClick = onClick,
            shape = RoundedCornerShape(4.dp),
            colors = ButtonDefaults.textButtonColors(backgroundColor = rwGreen),
            elevation = ButtonDefaults.elevation(
                defaultElevation = 2.dp,
                pressedElevation = 4.dp,
            ),
        ) {
            Text(text = "没有更多了哦~ | Click TOP", color = rwGreenDark)
        }
    }
}
