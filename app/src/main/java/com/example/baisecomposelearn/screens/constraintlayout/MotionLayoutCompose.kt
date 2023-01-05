package com.example.baisecomposelearn.screens.constraintlayout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.screens.components.goBack
import com.example.baisecomposelearn.theme.best

@Composable
fun MotionLayoutCompose(navController: NavController) {
    Column {
        var progress by remember {
            mutableStateOf(0f)
        }
        ProfileHeader(progress = progress)
        Spacer(modifier = Modifier.height(32.dp))
        Slider(
            value = progress,
            onValueChange = {
                progress = it
            },
            modifier = Modifier.padding(horizontal = 32.dp)
        )
        goBack(navController = navController)
    }

}

@OptIn(ExperimentalMotionApi::class)
@Composable
private fun ProfileHeader(progress: Float) {
    val context = LocalContext.current
    val motionScene = remember {
        context.resources.openRawResource(R.raw.motion_scene).readBytes().decodeToString()
    }
    MotionLayout(
        motionScene = MotionScene(content = motionScene),
        progress = progress,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.DarkGray)
                .layoutId("box")
        )
        Image(
            painter = painterResource(id = R.drawable.poster),
            contentDescription = "Poster",
            modifier = Modifier
                .clip(
                    CircleShape
                )
                .border(width = 2.dp, color = Color.Green, shape = CircleShape)
                .layoutId("profile_pic")
        )
        Text(
            text = "TestMotionLayoutCompose",
            fontFamily = best,
            fontSize = 24.sp,
            modifier = Modifier.layoutId("username")
        )
    }
}

@Preview
@Composable
fun MotionLayoutPreview() {
    MotionLayoutCompose(navController = rememberNavController())
}