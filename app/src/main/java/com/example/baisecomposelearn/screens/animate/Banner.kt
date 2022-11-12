package com.example.baisecomposelearn.screens.animate

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.screens.components.ScreenModel
import com.example.baisecomposelearn.screens.components.randomSampleImageUrl
import com.example.baisecomposelearn.utils.shortToast
import com.zj.banner.BannerPager
import com.zj.banner.model.BaseBannerBean
import com.zj.banner.ui.config.BannerConfig

@Composable
fun Banner(navController: NavController) {
    val item1 = arrayListOf(
        BannerBean(randomSampleImageUrl()),
        BannerBean(randomSampleImageUrl()),
        BannerBean(randomSampleImageUrl()),
        BannerBean(randomSampleImageUrl()),
    )
    val item2 = arrayListOf(
        BannerBean(randomSampleImageUrl()),
        BannerBean(randomSampleImageUrl()),
        BannerBean(randomSampleImageUrl()),
        BannerBean(randomSampleImageUrl()),
    )
    val item3 = arrayListOf(
        BannerBean(randomSampleImageUrl()),
        BannerBean(randomSampleImageUrl()),
        BannerBean(randomSampleImageUrl()),
        BannerBean(randomSampleImageUrl()),
    )
    val context = LocalContext.current
    ScreenModel(navController = navController, content = {
        BannerPager(items = item1) { item ->
            context.shortToast("item:$item")
        }
        Spacer(modifier = Modifier.height(5.dp))
        BannerPager(
            items = item2,
            indicatorGravity = Alignment.BottomEnd
        ) { item ->
            context.shortToast("item:$item")
        }
        Spacer(modifier = Modifier.height(5.dp))
        BannerPager(
            items = item3,
            indicatorGravity = Alignment.BottomStart,
            config = BannerConfig(
                bannerHeight = 250.dp,
                bannerImagePadding = 0.5.dp,
                shape = RoundedCornerShape(5),
                intervalTime = 5000
            )
        ) { item ->
            context.shortToast("item:$item")
        }
    })
}


data class BannerBean(
    override val data: Any? = null
) : BaseBannerBean()

@Preview
@Composable
fun BannerPrivew() {
    Banner(navController = rememberNavController())
}