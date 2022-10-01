package com.example.baisecomposelearn.screens.activate

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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.screens.components.ScreenModel
import com.example.baisecomposelearn.theme.best
import com.example.baisecomposelearn.utils.FlashUtils
import timber.log.Timber

@Composable
fun FlashLightScreen(navController: NavController) {
    var checked by remember { mutableStateOf(false) }
    val utils = FlashUtils(LocalContext.current)
    ScreenModel(navController = navController, content = {
        IconToggleButton(
            modifier = Modifier
                .size(120.dp)
                .background(shape = CircleShape, color = MaterialTheme.colors.onPrimary)
                .clip(shape = CircleShape),
            checked = checked,
            onCheckedChange = { checked = it }) {
            if (checked) {
                try {
                    utils.open()
                } catch (e: Exception) {
                    Timber.d("没有闪光灯：" + e.toString())
                }

            } else {
                try {
                    utils.close()
                }catch (e:Exception) {
                    Timber.d("没有闪光灯：" + e.toString())
                }

            }
            Row {
                val tint by animateColorAsState(targetValue = if (checked) Color.Red else Color.LightGray)
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

@Preview
@Composable
fun FlashLightScreenPreview() {
    FlashLightScreen(navController = rememberNavController())
}
