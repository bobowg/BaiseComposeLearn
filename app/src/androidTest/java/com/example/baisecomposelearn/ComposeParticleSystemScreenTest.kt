package com.example.baisecomposelearn

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.screens.media.ComposeParticleScreen
import com.example.baisecomposelearn.theme.BaiseComposeLearnTheme
import org.junit.Rule
import org.junit.Test

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
}