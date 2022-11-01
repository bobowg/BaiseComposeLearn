package com.example.baisecomposelearn

import android.Manifest
import android.content.*
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.provider.Settings
import android.net.Uri
import android.view.View
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.baisecomposelearn.location.CurrentLocationService
import com.example.baisecomposelearn.location.SharedPreferenceUtil
import com.example.baisecomposelearn.screens.media.ErrorContent
import com.example.baisecomposelearn.theme.BaiseComposeLearnTheme
import com.google.gson.Gson
import com.qweather.sdk.bean.base.Code
import com.qweather.sdk.bean.base.Lang
import com.qweather.sdk.bean.base.Unit
import com.qweather.sdk.bean.weather.WeatherNowBean
import com.qweather.sdk.bean.weather.WeatherNowBean.NowBaseBean
import com.qweather.sdk.view.QWeather


private const val TAG = "WeatherActivity"
private const val REQUEST_FOREGROUND_ONLY_PERMISSIONS_REQUEST_CODE = 34

class WeatherActivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener {
    private var foregroundOnlyLocationServiceBound = false
    private var currentOnlyLocationService: CurrentLocationService? = null
    private lateinit var foregroundOnlyBroadcastReceiver: ForegroundOnlyBroadcastReceiver
    private lateinit var sharedPreferences: SharedPreferences
    private val foregroundOnlyServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val binder = service as CurrentLocationService.LocalBinder
            currentOnlyLocationService = binder.service
            foregroundOnlyLocationServiceBound = true
            currentOnlyLocationService?.subscribeToLocationUpdates()
        }

        override fun onServiceDisconnected(name: ComponentName) {
            currentOnlyLocationService = null
            foregroundOnlyLocationServiceBound = false
        }
    }
    private var locations: Location? = null
    private var now: NowBaseBean? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()
        foregroundOnlyBroadcastReceiver = ForegroundOnlyBroadcastReceiver()
        sharedPreferences =
            getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE)
        val enabled =
            sharedPreferences.getBoolean(SharedPreferenceUtil.KEY_FOREGROUND_ENABLED, false)
        if (enabled) {
            currentOnlyLocationService?.unSubscribeToLocationUpdates()
        } else {
            if (foregroundPermissionApproved()) {
                currentOnlyLocationService?.subscribeToLocationUpdates() ?: Log.d(
                    "TAG",
                    "Service Not Bind"
                )
            } else {
                requestForegroundPermissions()
            }
        }
        if (locations != null) {
            QWeather.getWeatherNow(this@WeatherActivity,
                "${locations?.longitude},${locations?.latitude}",
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
                            now = weatherBean.now

                        } else {
                            //在此查看返回数据失败的原因
                            val code = weatherBean.code
                            Log.i(ContentValues.TAG, "failed code: $code")
                        }
                    }
                })
        }
        else{
            setContent{
                ErrorContent {

                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        updateButtonState(
            sharedPreferences.getBoolean(SharedPreferenceUtil.KEY_FOREGROUND_ENABLED, false)
        )
        sharedPreferences.registerOnSharedPreferenceChangeListener(this)

        val serviceIntent = Intent(this, CurrentLocationService::class.java)
        bindService(serviceIntent, foregroundOnlyServiceConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onResume() {
        super.onResume()
        LocalBroadcastManager.getInstance(this).registerReceiver(
            foregroundOnlyBroadcastReceiver,
            IntentFilter(
                CurrentLocationService.ACTION_FOREGROUND_ONLY_LOCATION_BROADCAST
            )
        )
    }

    override fun onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(
            foregroundOnlyBroadcastReceiver
        )
        super.onPause()
    }

    override fun onStop() {
        if (currentOnlyLocationService != null) {
            currentOnlyLocationService?.unSubscribeToLocationUpdates()
        }

        if (foregroundOnlyLocationServiceBound) {
            unbindService(foregroundOnlyServiceConnection)
            foregroundOnlyLocationServiceBound = false
        }
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)

        super.onStop()
    }

    private inner class ForegroundOnlyBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            var location = intent?.getParcelableExtra<Location>(
                CurrentLocationService.EXTRA_LOCATION
            )
            if (location != null) {
                location = location
                setContent {
                    BaiseComposeLearnTheme {

                    }
                }
            }
        }
    }

    private fun updateButtonState(trackingLocation: Boolean) {
        //Update the location here #trackingLocation
    }

    private fun foregroundPermissionApproved(): Boolean {
        return PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    }

    private fun requestForegroundPermissions() {
        val provideRationale = foregroundPermissionApproved()

        if (provideRationale) {
            ActivityCompat.requestPermissions(
                this@WeatherActivity,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_FOREGROUND_ONLY_PERMISSIONS_REQUEST_CODE
            )
        } else {
            ActivityCompat.requestPermissions(
                this@WeatherActivity,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_FOREGROUND_ONLY_PERMISSIONS_REQUEST_CODE
            )
        }
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String) {
        if (key == SharedPreferenceUtil.KEY_FOREGROUND_ENABLED) {
            updateButtonState(
                sharedPreferences.getBoolean(
                    SharedPreferenceUtil.KEY_FOREGROUND_ENABLED, false
                )
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Log.d(TAG, "onRequestPermissionResult")

        when (requestCode) {
            REQUEST_FOREGROUND_ONLY_PERMISSIONS_REQUEST_CODE -> when {
                grantResults.isEmpty() ->
                    Log.d(TAG, "User interaction was cancelled.")
                grantResults[0] == PackageManager.PERMISSION_GRANTED ->
                    currentOnlyLocationService?.subscribeToLocationUpdates()
                else -> {
                    updateButtonState(false)
                    val intent = Intent()
                    intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                    val uri = Uri.fromParts(
                        "package",
                        BuildConfig.APPLICATION_ID,
                        null
                    )
                    intent.data = uri
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                }
            }
        }
    }
}

