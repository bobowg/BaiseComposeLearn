package com.example.baisecomposelearn.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.screens.components.ScreenModel

@Composable
fun AnimationContentSizeScreen(navController: NavController) {
    var expanded by remember { mutableStateOf(false) }
    ScreenModel(navController = navController, content = {
        Button(
            onClick = { expanded = !expanded },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = if (expanded) stringResource(
                    id = R.string.animationcontentsize,
                    "缩小"
                ) else stringResource(
                    id = R.string.animationcontentsize, "展开"
                ),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .background(MaterialTheme.colors.surface.copy(alpha = 0.2f))
                .animateContentSize()
        ) {
            Text(
                modifier = Modifier.padding(16.dp),
                text = stringResource(id = R.string.content),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.White,
                textAlign = TextAlign.Justify,
                maxLines = if (expanded) Int.MAX_VALUE else 2
            )
        }
    })
}

@Preview
@Composable
fun AnimationContentSizeScreenPreview() {
    AnimationContentSizeScreen(navController = rememberNavController())
}