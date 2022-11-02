package com.example.baisecomposelearn.screens.media

import android.content.ContentValues.TAG
import android.location.Location
import android.os.Build
import android.util.Log
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
import androidx.compose.ui.unit.sp
import androidx.glance.LocalContext
import com.airbnb.lottie.compose.*
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.theme.backgroundColor
import com.google.android.gms.maps.MapView
import com.google.gson.Gson
import com.qweather.sdk.bean.base.Code
import com.qweather.sdk.bean.base.Lang
import com.qweather.sdk.bean.weather.WeatherNowBean
import com.qweather.sdk.view.QWeather
import com.qweather.sdk.view.QWeather.OnResultWeatherNowListener
import com.qweather.sdk.bean.base.Unit


private var locations: Location? = null
private lateinit var mapView: MapView


@Composable
fun WeatherScreen() {
    val context = LocalContext.current
    QWeather.getWeatherNow(
        context,
        "CN101010100",
        Lang.ZH_HANS,
        Unit.METRIC,
        object : OnResultWeatherNowListener {
            override fun onError(e: Throwable) {
                Log.i(TAG, "getWeather onError: $e")
            }

            override fun onSuccess(weatherBean: WeatherNowBean) {
                Log.i(TAG, "getWeather onSuccess: " + Gson().toJson(weatherBean))
                //先判断返回的status是否正确，当status正确时获取数据，若status不正确，可查看status对应的Code值找到原因
                if (Code.OK === weatherBean.code) {
                    val now = weatherBean.now
                } else {
                    //在此查看返回数据失败的原因
                    val code: Code = weatherBean.code
                    Log.i(TAG, "failed code: $code")
                }
            }
        })
    LoadingWeather()
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun LoadingWeather(
    modifier: Modifier = Modifier,
    getFeelsLike: String = "", //体感
    getTemp: String = "", //温度
    getIcon: String = "", //图标类型
    getText: String = "", // 实况天气状况代码:晴
    getWindDir: String = "", //风向:西北
    getWindScale: String = "",//风速级别：3-4级
    getWindSpeed: String = "", //风速，公里/小时
    getHumidity: String = "", //相对湿度
    getPrecip: String = "", //降水量
    getPressure: String = "",//大气压强
    getVis: String = "", //能见度，默认单位：10公里
    getCloud: String = "", //云量23
    getDew: String = "", //实况云量23
) {
    Card(
        backgroundColor = backgroundColor,
        shape = RoundedCornerShape(10.dp),
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = getFeelsLike,
                color = Color.Red,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp
            )
            Text(text = getTemp, color = Color.Red, fontWeight = FontWeight.Bold, fontSize = 28.sp)
            Text(text = getText, color = Color.Red, fontWeight = FontWeight.Bold, fontSize = 28.sp)
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
    LoadingWeather(
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
