package com.example.baisecomposelearn.screens.media

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.theme.BaiseComposeLearnTheme
import com.example.baisecomposelearn.theme.best
import com.example.baisecomposelearn.theme.rwRed
import com.example.baisecomposelearn.utils.dp2px
import com.example.baisecomposelearn.wanandroid.Project
import com.example.baisecomposelearn.wanandroid.WanandroidViewModel
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun WanandroidScreen() {
    val vm: WanandroidViewModel = viewModel()
    val projects = vm.projects.collectAsLazyPagingItems()
    when (projects.loadState.refresh) {
        is LoadState.NotLoading -> LazyColumn {
            itemsIndexed(projects) { _, project ->
                ProjectItem(project = project!!)
            }
        }
        is LoadState.Error -> ErrorPage { projects.refresh() }
        LoadState.Loading -> LoadingPage()
    }

}


@Composable
fun ProjectItem(project: Project) {
    val context = LocalContext.current
    Card(modifier = Modifier.padding(8.dp)) {
        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = project.title, style = MaterialTheme.typography.h6, fontFamily = best
                )
                TextButton(onClick = { HtmlLink(project.link, context) }) {
                    Text(
                        text = project.desc,
                        style = MaterialTheme.typography.body1,
                        fontFamily = best,
                        modifier = Modifier.padding(top = 6.dp)
                    )
                }
                TextButton(onClick = { HtmlLink(project.projectLink, context) }) {
                    Text(
                        text = "项目地址" + project.projectLink,
                        style = MaterialTheme.typography.body1,
                        fontFamily = best,
                        modifier = Modifier.padding(top = 6.dp)
                    )
                }
                Row(modifier = Modifier.padding(top = 6.dp)) {
                    Text(
                        text = project.author,
                        style = MaterialTheme.typography.body1,
                        color = Color.Gray,
                        fontFamily = best
                    )
                    Text(
                        text = project.niceDate,
                        style = MaterialTheme.typography.body1,
                        color = Color.Gray,
                        fontFamily = best,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                }
            }
            CoilImage(
                imageModel = project.envelopePic,
                modifier = Modifier
                    .width(57.dp)
                    .height(101.dp)
                    .padding(start = 4.dp),
                circularReveal = CircularReveal(duration = 300),
                placeHolder = Icons.Filled.Image,
                error = Icons.Filled.Error
            )
        }
    }
}

fun HtmlLink(text: String, context: Context) {
    try {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.addCategory(Intent.CATEGORY_BROWSABLE)
        intent.data = Uri.parse(text)
        startActivity(context, intent, null)
    } catch (e: Exception) {
        Toast.makeText(context, "没有安浏览器", Toast.LENGTH_SHORT).show()
    }
}

@Preview
@Composable
fun ProjectItemPreview() {
    ProjectItem(
        project = Project(
            "bobowg",
            "https://img.zcool.cn/community/01ad5b5af51395a801216045fb3729.jpg@2o.jpg",
            "maihaoming",
            "2022-10-11",
            "注解表明该函数为构建UI的Compose函数，且函数首字母必须大写。",
            "https://www.wanandroid.com/blog/show/3436",
            "https://www.wanandroid.com/blog/show/3436"
        )
    )
}

@Composable
fun ErrorPage(onclick: () -> Unit = {}) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.no_signal),
            contentDescription = "没有信号输出",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
        )
        Button(onClick = onclick, modifier = Modifier.padding(8.dp)) {
            Text(text = "网络不佳，请重试!")
        }
    }
}

@Preview
@Composable
fun ErrorPagePreview() {
    BaiseComposeLearnTheme {
        ErrorPage(onclick = {})
    }
}

@Composable
fun LoadingPage(context: Context = LocalContext.current) {
    val animate by rememberInfiniteTransition().animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(tween(500, easing = LinearEasing), RepeatMode.Restart)
    )
    val radius = context.dp2px(80f)
    Canvas(modifier = Modifier.fillMaxSize()) {
        translate(size.width / 2 - radius, size.height / 2 - radius) {
            drawArc(
                rwRed,
                0f,
                animate,
                false,
                size = Size(radius * 2f, radius * 2f),
                style = Stroke(context.dp2px(4f)),
                alpha = 0.6f
            )
        }
    }
}

@Preview
@Composable
fun LoadingPagePreview() {
    BaiseComposeLearnTheme {
        LoadingPage()
    }
}
