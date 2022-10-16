package com.example.baisecomposelearn

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.provider.FontRequest
import androidx.emoji2.bundled.BundledEmojiCompatConfig
import androidx.emoji2.text.EmojiCompat
import androidx.emoji2.text.FontRequestEmojiCompatConfig
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.appdrawer.Drawer
import com.example.baisecomposelearn.appdrawer.TopAppBar
import com.example.baisecomposelearn.dependencyinjector.DependencyInjector
import com.example.baisecomposelearn.navitegation.Navigation
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class AppStart : Application() {
    lateinit var dependencyInjector: DependencyInjector

    companion object {

        private const val TAG = "EmojiCompatApplication"

        /** Change this to `false` when you want to use the downloadable Emoji font.  */
        private const val USE_BUNDLED_EMOJI = true

    }

    override fun onCreate() {
        super.onCreate()
        initDependencyInjector()
        val config: EmojiCompat.Config
        if (USE_BUNDLED_EMOJI) {
            // Use the bundled font for EmojiCompat
            config = BundledEmojiCompatConfig(applicationContext)
        } else {
            // Use a downloadable font for EmojiCompat
            val fontRequest = FontRequest(
                "com.google.android.gms.fonts",
                "com.google.android.gms",
                "Noto Color Emoji Compat",
                R.array.com_google_android_gms_fonts_certs
            )
            config = FontRequestEmojiCompatConfig(applicationContext, fontRequest)
                .setReplaceAll(true)
                .registerInitCallback(object : EmojiCompat.InitCallback() {
                    override fun onInitialized() {
                        Log.i(TAG, "EmojiCompat initialized")
                    }

                    override fun onFailed(throwable: Throwable?) {
                        Log.e(
                            TAG,
                            "EmojiCompat initialization failed",
                            throwable
                        )
                    }
                })
        }
        EmojiCompat.init(config)
    }

    private fun initDependencyInjector() {
        dependencyInjector = DependencyInjector(this)
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun StartApp() {
    val scaffoldState =
        rememberScaffoldState(rememberDrawerState(initialValue = DrawerValue.Closed))
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(scope = scope, scoffoldState = scaffoldState)
        },
        drawerBackgroundColor = colorResource(id = R.color.colorPrimary),
        drawerContent = {
            Drawer(scope = scope, scoffoldState = scaffoldState, navController = navController)
        },
        contentColor = colorResource(id = R.color.colorPrimary),
        content = { padding ->
            Surface(modifier = Modifier.padding(padding)) {
                Navigation(navController = navController)
            }
        }
    )
}

@RequiresApi(Build.VERSION_CODES.P)
@Preview
@Composable
fun StartAppPreview() {
    StartApp()
}