package com.example.baisecomposelearn

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.baisecomposelearn.screens.animate.CustomCountDownTimer
import com.example.baisecomposelearn.screens.animate.ElevationAnimationScreen
import com.example.baisecomposelearn.screens.animate.RotateAnimationScreen
import com.example.baisecomposelearn.screens.animate.SingleValueFloatAnimationScreen
import com.example.baisecomposelearn.screens.constraintlayout.BottomSheetScaffoldScreen
import com.example.baisecomposelearn.screens.customexamples.CollapsingToolbar
import com.example.baisecomposelearn.screens.customexamples.EmojiCompoatScreen
import com.example.baisecomposelearn.theme.BaiseComposeLearnTheme
import me.rerere.zoomableimage.ZoomableImage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SingleValueFloatAnimationScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testSingleValueFloatAnimationScreen() {
        composeTestRule.setContent {
            BaiseComposeLearnTheme {
                SingleValueFloatAnimationScreen(navController = rememberNavController())
            }
        }
    }

    @Test
    fun testElevationAnimationScreen() {
        composeTestRule.setContent {
            BaiseComposeLearnTheme {
                ElevationAnimationScreen(navController = rememberNavController())
            }
        }
    }

    @Test
    fun testBottomSheetScaffoldScreen() {
        composeTestRule.setContent {
            BaiseComposeLearnTheme {
                BottomSheetScaffoldScreen(navController = rememberNavController())
            }
        }
    }

    @Test
    fun testEmojiCompoatScreen() {
        composeTestRule.setContent {
            BaiseComposeLearnTheme {
                EmojiCompoatScreen(navController = rememberNavController())
            }
        }
    }

    @Test
    fun testCollapsingToolbar() {
        composeTestRule.setContent {
            BaiseComposeLearnTheme {
                CollapsingToolbar()
            }
        }
    }

    @Test
    fun testCustomCountDownTimer() {
        composeTestRule.setContent {
            BaiseComposeLearnTheme {
                CustomCountDownTimer()
            }
        }
    }
    @Test
    fun testZoomableComposeImageExample() {
        composeTestRule.setContent {
            BaiseComposeLearnTheme {
               ZoomableImage(painter = painterResource(id = R.drawable.poster), modifier = Modifier.fillMaxSize())
            }
        }
    }
    @Test
    fun testRotateAnimationScreen() {
        composeTestRule.setContent {
            BaiseComposeLearnTheme {
                RotateAnimationScreen(navController = rememberNavController())
            }
        }
    }
}