package com.example.baisecomposelearn.screens.media

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.screens.components.ScreenModel
import com.example.baisecomposelearn.theme.rwRed
import com.halilibo.richtext.ui.Heading
import com.halilibo.richtext.ui.RichText
import com.halilibo.richtext.ui.material.MaterialRichText
import com.halilibo.richtext.ui.material.SetupMaterialRichText

@Composable
fun MaterialRichTextScreen(navController: NavController) {
    ScreenModel(navController = navController, content = {
        MaterialRichText(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(color = rwRed)
        ) {
            Heading(level = 0, text = "标题")
            Text(
                text = stringResource(id = R.string.content),

                )
        }
        SetupMaterialRichText {
            RichText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(color = rwRed)
            ) {
                Heading(0, "标题")
                Text(
                    text = stringResource(id = R.string.content),

                    )
            }
        }
    })
}

@Preview
@Composable
fun MaterialRichTextPreview() {
    MaterialRichTextScreen(navController = rememberNavController())
}