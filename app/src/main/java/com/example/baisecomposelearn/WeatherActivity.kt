package com.example.baisecomposelearn

import android.Manifest
import android.content.ContentValues
import android.content.Intent
import android.location.Location
import android.location.LocationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.example.baisecomposelearn.screens.media.ErrorContent
import com.example.baisecomposelearn.screens.media.WeatherScreen
import com.example.baisecomposelearn.theme.BaiseComposeLearnTheme
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.gson.Gson
import com.qweather.sdk.bean.base.Code
import com.qweather.sdk.bean.base.Lang
import com.qweather.sdk.bean.base.Unit
import com.qweather.sdk.bean.weather.WeatherNowBean
import com.qweather.sdk.view.QWeather

class WeatherActivity : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()
        var latitudi: Double = 0.0
        var longtitude: Double = 0.0
        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                    // Precise location access granted.
                }
                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                    // Only approximate location access granted.
                }
                else -> {
                    val sendIntent = Intent(this, MainActivity::class.java)
                    ContextCompat.startActivity(this, sendIntent, Bundle.EMPTY)
                }
            }
        }
        locationPermissionRequest.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        QWeather.getWeatherNow(this@WeatherActivity,
            "$longtitude,$latitudi",
            Lang.ZH_HANS,
            Unit.METRIC,
            object : QWeather.OnResultWeatherNowListener {
                override fun onError(e: Throwable) {
                    Log.i(ContentValues.TAG, "getWeather onError: $e")
                }

                override fun onSuccess(weatherBean: WeatherNowBean) {
                    Log.i(ContentValues.TAG, "getWeather onSuccess: " + Gson().toJson(weatherBean))
                    //先判断返回的status是否正确，当status正确时获取数据，若status不正确，可查看status对应的Code值找到原因
                    if (Code.OK == weatherBean.code) {
                        val now = weatherBean.now
                    } else {
                        //在此查看返回数据失败的原因
                        val code = weatherBean.code
                        Log.i(ContentValues.TAG, "failed code: $code")
                    }
                }
            })

        setContent {
            BaiseComposeLearnTheme {
                WeatherScreen()
            }
        }
    }
}