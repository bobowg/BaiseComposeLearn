package com.example.baisecomposelearn

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.baisecomposelearn.screens.animate.SingleValueFloatAnimationScreen
import com.example.baisecomposelearn.screens.media.ComposeParticleScreen
import com.example.baisecomposelearn.theme.BaiseComposeLearnTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ComposeParticleSystemScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testComposeParticleSystemScreen(){
        composeTestRule.mainClock.autoAdvance = false
        composeTestRule.setContent {
            BaiseComposeLearnTheme {
                ComposeParticleScreen(rememberNavController())
            }
        }
        composeTestRule.mainClock.advanceTimeBy(10 * 1000)
    }
    @Test
    fun testSingleValueFloatAnimationScreen(){
        composeTestRule.setContent {
            BaiseComposeLearnTheme {
                SingleValueFloatAnimationScreen(rememberNavController())
            }
        }
    }
}