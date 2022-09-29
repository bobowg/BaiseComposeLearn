package com.example.baisecomposelearn.screens.activate

import android.content.Context
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.screens.components.ScreenModel
import com.example.baisecomposelearn.theme.best
import java.util.concurrent.Executor

@Composable
fun FlashLightScreen(navController: NavController) {
    val context = LocalContext.current
    var checked by remember { mutableStateOf(false) }
    ScreenModel(navController = navController, content = {
        IconToggleButton(
            modifier = Modifier
                .size(120.dp)
                .background(shape = CircleShape, color = MaterialTheme.colors.onPrimary)
                .clip(shape = CircleShape),
            checked = checked,
            onCheckedChange = { checked = it }) {
            Row {
                val tint by animateColorAsState(targetValue = if (checked) Color.Red else Color.LightGray)
                FlashLightOnOrOFF(checked, context)
                Text(
                    text = stringResource(id = R.string.flashlight),
                    fontFamily = best,
                    fontWeight = FontWeight.Bold,
                    color = tint
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_highlight),
                    contentDescription = stringResource(
                        id = R.string.flashlight
                    ),
                    tint = tint
                )
            }

        }
    })
}

fun FlashLightOnOrOFF(checked: Boolean, context: Context) {
    val cameraProcessFuture = ProcessCameraProvider.getInstance(context)
    cameraProcessFuture.addListener(Runnable {
        val cameraProvider = cameraProcessFuture.get()
        val lifecycleOwner: LifecycleOwner? = null
        val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
        val imageAnalysis = ImageAnalysis.Builder().build().apply {
            setAnalyzer(Executor { }, ImageAnalysis.Analyzer { it.close() })

        }
        val camera = lifecycleOwner?.let { cameraProvider.bindToLifecycle(it, cameraSelector) }
        val cameraControl = camera?.cameraControl
        if (checked){
            cameraControl?.enableTorch(true)
        }else{
            cameraControl?.enableTorch(false)
        }
    },ContextCompat.getMainExecutor(context)
    )
}


@Preview
@Composable
fun FlashLightScreenPreivew() {
    FlashLightScreen(navController = rememberNavController())
}