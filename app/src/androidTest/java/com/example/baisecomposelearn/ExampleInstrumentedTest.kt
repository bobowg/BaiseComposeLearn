package com.example.baisecomposelearn

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.baisecomposelearn.screens.animate.AnimatingFonts
import com.example.baisecomposelearn.screens.animate.DraggableMusicKnob
import com.example.baisecomposelearn.screens.constraintlayout.CustomCalendarView
import com.example.baisecomposelearn.screens.constraintlayout.CustomShape
import com.example.baisecomposelearn.screens.constraintlayout.MotionLayoutCompose
import com.example.baisecomposelearn.screens.customexamples.RatingBarScreen
import com.example.baisecomposelearn.screens.customexamples.SystemUiControllerAccompanistScreen
import com.example.baisecomposelearn.screens.foundation.MultiLayerPrallax
import com.example.baisecomposelearn.screens.media.ErrorPage
import com.example.baisecomposelearn.screens.media.ProjectItem
import com.example.baisecomposelearn.theme.BaiseComposeLearnTheme
import com.example.baisecomposelearn.wanandroid.Project
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
    fun testRatingBarScreen() {
        composeTestRule.setContent {
            BaiseComposeLearnTheme {
                RatingBarScreen(navController = rememberNavController())
            }
        }
    }

    @Test
    fun testSystemUiControllerAccompanistScreen() {
        composeTestRule.setContent {
            BaiseComposeLearnTheme {
                SystemUiControllerAccompanistScreen(navController = rememberNavController())
            }
        }
    }

    @Test
    fun testCustomShapeScreen() {
        composeTestRule.setContent {
            BaiseComposeLearnTheme {
                CustomShape(navController = rememberNavController())
            }
        }
    }

    @Test
    fun testProjectlist() {
        composeTestRule.setContent {
            BaiseComposeLearnTheme {
                ProjectItem(
                    project = Project(
                        "bobowg",
                        "https://img.zcool.cn/community/01ad5b5af51395a801216045fb3729.jpg@2o.jpg",
                        "maihaoming",
                        "2022-10-11",
                        "注解表明该函数为构建UI的Compose函数，且函数首字母必须大写。",
                        "注解表明该函数为构建UI的Compose函数，且函数首字母必须大写。",
                        "注解表明该函数为构建UI的Compose函数，且函数首字母必须大写。"
                    )
                )
            }
        }
    }

    @Test
    fun testErrorPage() {
        composeTestRule.setContent {
            BaiseComposeLearnTheme {
                ErrorPage()
            }
        }
    }

    @Test
    fun testCustomCalendarView() {
        composeTestRule.setContent {
            BaiseComposeLearnTheme {
                CustomCalendarView(navController = rememberNavController())
            }
        }
    }

    @Test
    fun testAnimatingFont(){
        composeTestRule.setContent {
            BaiseComposeLearnTheme {
                AnimatingFonts(navController = rememberNavController())
            }
        }
    }

    @Test
    fun testDraggableMusicKnob(){
        composeTestRule.setContent {
            BaiseComposeLearnTheme {
                DraggableMusicKnob(rememberNavController())
            }
        }
    }
    @Test
    fun testMotionLayoutCompose(){
        composeTestRule.setContent {
            BaiseComposeLearnTheme {
                MotionLayoutCompose(rememberNavController())
            }
        }
    }
    @Test
    fun testMultiLayerPrallax(){
        composeTestRule.setContent {
            BaiseComposeLearnTheme {
                MultiLayerPrallax(navController = rememberNavController())
            }
        }
    }
}