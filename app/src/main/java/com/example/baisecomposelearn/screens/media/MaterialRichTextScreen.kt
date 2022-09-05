package com.example.baisecomposelearn.screens.media

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.screens.components.ScreenModel
import com.halilibo.richtext.ui.Heading
import com.halilibo.richtext.ui.RichText
import com.halilibo.richtext.ui.material.MaterialRichText
import com.halilibo.richtext.ui.material.SetupMaterialRichText

@Composable
fun MaterialRichTextScreen(navController: NavController) {
    ScreenModel(navController = navController, content = {
        MaterialRichText(modifier = Modifier.fillMaxSize().padding(16.dp).background(color = Color.White)) {
            Heading(level = 0, text = "标题")
            Text(text = "简单的示例")
        }
        SetupMaterialRichText {
            RichText(modifier = Modifier.fillMaxWidth().padding(16.dp).background(color = Color.White)) {
                Heading(0, "标题")
                Text("简单的示例")
            }
        }
    })
}

@Preview
@Composable
fun MaterialRichTextPreview() {
    MaterialRichTextScreen(navController = rememberNavController())
}