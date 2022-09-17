package com.example.baisecomposelearn.utils

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults.elevation
import androidx.compose.material.ButtonDefaults.textButtonColors
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.example.baisecomposelearn.theme.rwGreen
import com.example.baisecomposelearn.theme.rwGreenDark
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.model.playviewmodel.PlayViewModel
import com.example.baisecomposelearn.screens.components.exoplayer.VideoCardItem
import com.example.baisecomposelearn.theme.rwRed


/**
 * 下拉加载封装
 *
 * implementation "com.google.accompanist:accompanist-swiperefresh:xxx"
 * */
@Composable
fun <T : Any> SwipeRefreshLayout(
    columnState: LazyListState?,
    collectAsLazyPagingItems: LazyPagingItems<T>,
    listContent: LazyListScope.() -> Unit,
) {

    val rememberSwipeRefreshState = rememberSwipeRefreshState(isRefreshing = false)

    SwipeRefresh(
        state = rememberSwipeRefreshState,
        onRefresh = { collectAsLazyPagingItems.refresh() }
    ) {

        rememberSwipeRefreshState.isRefreshing =
            collectAsLazyPagingItems.loadState.refresh is LoadState.Loading

        LazyColumn(
            state = columnState ?: rememberLazyListState(),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),

            ) {
            listContent()
            collectAsLazyPagingItems.apply {
                when {
                    loadState.append is LoadState.Loading -> {
                        //加载更多，底部loading
                        item { LoadingItem() }
                    }
                    loadState.append is LoadState.Error -> {
                        //加载更多异常
                        item {
                            ErrorMoreRetryItem() {
                                collectAsLazyPagingItems.retry()
                            }
                        }
                    }
                    loadState.append == LoadState.NotLoading(endOfPaginationReached = true) -> {
                        // 没有更多数据了
                        item { NoMoreDataFindUI() }
                    }
                    loadState.refresh is LoadState.Error -> {
                        if (collectAsLazyPagingItems.itemCount <= 0) {
                            //刷新的时候，如果itemCount小于0，第一次加载异常
                            item {
                                ErrorContent() {
                                    collectAsLazyPagingItems.retry()
                                }
                            }
                        } else {
                            item {
                                ErrorMoreRetryItem() {
                                    collectAsLazyPagingItems.retry()
                                }
                            }
                        }
                    }
                    loadState.refresh is LoadState.Loading -> {
                        // 第一次加载且正在加载中
                        if (collectAsLazyPagingItems.itemCount == 0) {
                        }
                    }
                }
            }

        }
    }
}

/**
 * 底部加载更多失败处理
 * */
@Composable
fun ErrorMoreRetryItem(retry: () -> Unit) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        TextButton(
            onClick = { retry() },
            modifier = Modifier
                .padding(20.dp)
                .width(80.dp)
                .height(30.dp),
            shape = RoundedCornerShape(6.dp),
            contentPadding = PaddingValues(3.dp),
            colors = textButtonColors(backgroundColor = rwGreen),
            elevation = elevation(
                defaultElevation = 2.dp,
                pressedElevation = 4.dp,
            ),
        ) {
            Text(text = "请重试", color = rwGreenDark)
        }
    }
}

/**
 * 底部加载更多到底了
 * */
@Composable
fun NoMoreDataFindUI() {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        TextButton(
            onClick = {},
            modifier = Modifier
                .padding(20.dp)
                .width(80.dp)
                .height(30.dp),
            shape = RoundedCornerShape(6.dp),
            contentPadding = PaddingValues(3.dp),
            colors = textButtonColors(backgroundColor = rwGreen),
            elevation = elevation(
                defaultElevation = 2.dp,
                pressedElevation = 4.dp,
            ),
        ) {
            Text(text = "没有更多了哦~", color = rwGreenDark)
        }
    }
}

/**
 * 页面加载失败处理
 * */
@Composable
fun ErrorContent(retry: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 100.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.padding(top = 80.dp),
            painter = painterResource(id = R.drawable.ic_default_empty),
            contentDescription = null
        )
        Text(text = "请求失败，请检查网络", modifier = Modifier.padding(8.dp))
        TextButton(
            onClick = { retry() },
            modifier = Modifier
                .padding(20.dp)
                .width(80.dp)
                .height(30.dp),
            shape = RoundedCornerShape(10.dp),
            contentPadding = PaddingValues(5.dp),
            colors = textButtonColors(backgroundColor = rwGreen),
            elevation = elevation(
                defaultElevation = 2.dp,
                pressedElevation = 4.dp,
            )
            //colors = ButtonDefaults
        ) { Text(text = "重试", color = rwGreenDark) }
    }
}

/**
 * 底部加载更多正在加载中...
 * */
@Composable
fun LoadingItem() {
    Row(
        modifier = Modifier
            .height(34.dp)
            .fillMaxWidth()
            .padding(5.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(24.dp),
            color = rwRed,
            strokeWidth = 2.dp
        )
        Text(
            text = "加载中...",
            color = rwRed,
            modifier = Modifier
                .fillMaxHeight()
                .padding(start = 20.dp),
            fontSize = 18.sp,
        )
    }
}

/**
 * 首页列表加载 ---下拉刷新，加载更多动效
 * */
@SuppressLint("UnrememberedMutableState")
@Composable
fun RefreshVideoListScreen(
    viewModel: PlayViewModel,
    context: Context,
) {

    val collectAsLazyPagingIDataList = viewModel.videoItemList.collectAsLazyPagingItems()

    val lazyListState = rememberLazyListState()
    val focusIndex by derivedStateOf { lazyListState.firstVisibleItemIndex }


    SwipeRefreshLayout(
        columnState = lazyListState,
        collectAsLazyPagingItems = collectAsLazyPagingIDataList
    ) {

        itemsIndexed(collectAsLazyPagingIDataList) { index, data ->
            VideoCardItem(
                videoItem = data!!,
                isFocused = index == focusIndex,
                onClick = { Toast.makeText(context, "ccc", Toast.LENGTH_SHORT).show() },
                index = index,
                viewModel = viewModel
            )
        }
    }
}
