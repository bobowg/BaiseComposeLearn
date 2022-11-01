package com.example.baisecomposelearn.screens.media

import android.location.Location
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.theme.backgroundColor
import com.google.android.gms.maps.MapView


private var locations: Location? = null
private lateinit var mapView: MapView

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherScreen(
    modifier: Modifier = Modifier,
    getFeelsLike: String? = "", //体感
    getTemp: String? = "", //温度
    getIcon: String? = "", //图标类型
    getText: String? = "", // 实况天气状况代码:晴
    getWindDir: String? = "", //风向:西北
    getWindScale: String? = "",//风速级别：3-4级
    getWindSpeed: String? = "", //风速，公里/小时
    getHumidity: String? = "", //相对湿度
    getPrecip: String? = "", //降水量
    getPressure: String? = "",//大气压强
    getVis: String? = "", //能见度，默认单位：10公里
    getCloud: String? = "", //云量23
    getDew: String? = "", //实况云量23
) {
    Card(
        backgroundColor = backgroundColor,
        shape = RoundedCornerShape(10.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = getFeelsLike!!, color = Color.Red, fontWeight = FontWeight.Bold)
        }
    }
}


@Composable
fun ErrorContent(
    modifier: Modifier = Modifier,
    onErrorClick: () -> kotlin.Unit
) {
    val compositon by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.heart_like))
    val progres by animateLottieCompositionAsState(
        composition = compositon,
        iterations = LottieConstants.IterateForever
    )
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.onSecondary),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(
            composition = compositon,
            progress = progres,
            modifier = Modifier.size(130.dp)
        )
        Button(onClick = { onErrorClick() }) {
            Text(text = stringResource(id = R.string.bad_network_view_tip))
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun WeatherScreenPreview() {
    WeatherScreen(
        getFeelsLike = "23℃",
        getTemp = "21℃",
        getIcon = "100",
        getText = "晴",
        getWindDir = "西北",
        getWindScale = "3-4级",
        getWindSpeed = "15公里/小时",
        getHumidity = "40",
        getPrecip = "降水量:0",
        getPressure = "大气压强:1020",
        getVis = "能见度10公里",
        getCloud = "云量:23",
        getDew = "实况云量:23"
    )
}


@Preview
@Composable
fun ErrorContentPreview() {
    ErrorContent {}
}
