package com.example.baisecomposelearn


import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.baisecomposelearn.screens.viewmodel.ViewModelFlowScreen
import com.example.baisecomposelearn.theme.BaiseComposeLearnTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp(){
        composeTestRule.setContent {
            BaiseComposeLearnTheme{
                ViewModelFlowScreen(rememberNavController())
            }
        }
    }
    @Test
    fun  verify_if_all_views_exists(){
        composeTestRule.onNodeWithTag("Counter Display").assertExists()
        composeTestRule.onNodeWithTag("input").assertExists()
        composeTestRule.onNodeWithText("返回").assertExists()
    }
    @Test
    fun counterValue_with_emptyInput_displays_InvalidEntry() {
        composeTestRule.onNodeWithText("返回").performClick()
    }
}