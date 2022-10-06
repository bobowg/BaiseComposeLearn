package com.example.baisecomposelearn

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.baisecomposelearn.screens.animate.ElevationAnimationScreen
import com.example.baisecomposelearn.screens.animate.SingleValueFloatAnimationScreen
import com.example.baisecomposelearn.screens.constraintlayout.BottomSheetScaffoldScreen
import com.example.baisecomposelearn.screens.customexamples.EmojiCompoatScreen
import com.example.baisecomposelearn.theme.BaiseComposeLearnTheme
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
}