package com.example.baisecomposelearn.screens.animate


import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.screens.components.ScreenModel

private enum class DemoScene {
    Text, Icon
}

@Composable
fun CrossFadeScreen(navController: NavController) {
    var scene by remember { mutableStateOf(DemoScene.Text) }
    ScreenModel(navController = navController, content = {
        Button(
            onClick = {
                scene = when (scene) {
                    DemoScene.Icon -> DemoScene.Text
                    DemoScene.Text -> DemoScene.Icon
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = when (scene) {
                    DemoScene.Text -> stringResource(id = R.string.crossfade, "文本")
                    DemoScene.Icon -> stringResource(id = R.string.crossfade, "图标")
                },
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.White

            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Crossfade(targetState = scene) { scene->
            when (scene) {
                DemoScene.Text -> Text(
                    text = stringResource(id = R.string.phone),
                    fontSize = 32.sp,
                    color = Color.White
                )
                DemoScene.Icon -> Icon(
                    imageVector = Icons.Default.Phone,
                    contentDescription = stringResource(
                        id = R.string.phone
                    ),
                    tint = Color.White
                )
            }
        }

        Divider(
            modifier = Modifier.padding(
                start = 16.dp,
                top = 16.dp,
                end = 16.dp,
                bottom = 10.dp
            ),
            color = Color.White,
            thickness = 4.dp
        )
    })
}

@Preview
@Composable
fun CrossFadeScreenPreview() {
    CrossFadeScreen(navController = rememberNavController())
}