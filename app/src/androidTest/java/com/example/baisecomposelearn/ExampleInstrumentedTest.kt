package com.example.baisecomposelearn

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.baisecomposelearn.navitegation.HapticSamplerDestinations
import com.example.baisecomposelearn.screens.constraintlayout.CustomShape
import com.example.baisecomposelearn.screens.customexamples.OrbitalExampleScreen
import com.example.baisecomposelearn.screens.customexamples.RatingBarScreen
import com.example.baisecomposelearn.screens.customexamples.SystemUiControllerAccompanistScreen
import com.example.baisecomposelearn.screens.vibration.bounce.BounceExampleScreen
import com.example.baisecomposelearn.screens.vibration.expand.ExpandExampleScreen
import com.example.baisecomposelearn.theme.BaiseComposeLearnTheme
import com.example.baisecomposelearn.theme.HapticSamplerTheme
import com.example.baisecomposelearn.vibration.appdraw.AppDrawer
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.baisecomposelearn", appContext.packageName)
    }

    @Test
    fun testAppDrawer() {
        composeTestRule.setContent {
            HapticSamplerTheme {
                AppDrawer(
                    HapticSamplerDestinations.HOME_ROUTE,
                    navigateToHome = {},
                    navigateToResist = {},
                    navigateToExpand = {},
                    navigateToBounce = {},
                    navigateToWobble = {},
                    closeDrawer = {},
                )
            }
        }
    }

    @Test
    fun testExpandExampleScreen() {
        composeTestRule.setContent {
            HapticSamplerTheme {
                ExpandExampleScreen(
                    messageToUser = "A message to display to user."
                )
            }
        }
    }

    @Test
    fun testBounceExampleScreen() {
        composeTestRule.setContent {
            HapticSamplerTheme {
                BounceExampleScreen(
                    messageToUser = "A message to display to user."
                )
            }
        }
    }
    @Test
    fun testRatingBarScreen(){
        composeTestRule.setContent {
            BaiseComposeLearnTheme {
                RatingBarScreen(navController = rememberNavController())
            }
        }
    }
    @Test
    fun testSystemUiControllerAccompanistScreen(){
        composeTestRule.setContent {
            BaiseComposeLearnTheme {
                SystemUiControllerAccompanistScreen(navController = rememberNavController())
            }
        }
    }
    @Test
    fun testCustomShapeScreen(){
        composeTestRule.setContent {
            BaiseComposeLearnTheme {
                CustomShape(navController = rememberNavController())
            }
        }
    }

}