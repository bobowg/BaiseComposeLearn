package com.example.baisecomposelearn

import android.app.Application
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.baisecomposelearn.navitegation.HapticSamplerDestinations
import com.example.baisecomposelearn.screens.vibration.expand.ExpandExampleScreen
import com.example.baisecomposelearn.screens.vibration.resist.ResistRoute
import com.example.baisecomposelearn.theme.HapticSamplerTheme
import com.example.baisecomposelearn.vibration.appdraw.AppDrawer
import com.example.baisecomposelearn.vibration.viewmodel.ResistViewModel
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
    fun testExpandExampleScreen(){
        composeTestRule.setContent {
            HapticSamplerTheme {
                ExpandExampleScreen(
                    messageToUser = "A message to display to user."
                )
            }
        }
    }
}