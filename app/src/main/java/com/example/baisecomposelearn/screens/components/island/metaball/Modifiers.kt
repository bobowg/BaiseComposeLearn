package com.example.baisecomposelearn.screens.components.island.metaball

import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer

@RequiresApi(Build.VERSION_CODES.S)
fun Modifier.customBlur(blur: Float) = this.then(
    graphicsLayer {
        renderEffect = RenderEffect.createBlurEffect(blur, blur, Shader.TileMode.DECAL)
            .asComposeRenderEffect()
    }
)