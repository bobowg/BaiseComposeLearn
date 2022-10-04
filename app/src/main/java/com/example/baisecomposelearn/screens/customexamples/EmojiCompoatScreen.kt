package com.example.baisecomposelearn.screens.customexamples


import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.screens.components.ScreenModel
import com.example.baisecomposelearn.utils.CustomTextView

@Composable
fun EmojiCompoatScreen(navController: NavController) {
    val context = LocalContext.current
    val man = "\uD83D\uDC69\u200D\uD83C\uDFA4"
    var textView = CustomTextView(context).text
    textView = "\uD83D\uDC69\u200D\uD83D\uDCBB"
    ScreenModel(navController = navController, content = {
        Text(
            text = stringResource(id = R.string.emojicompoat) + man + textView, fontSize = 28.sp,
            color = MaterialTheme.colors.onPrimary
        )
        TextField(
            value = stringResource(id = R.string.emojicompoat) + textView + man,
            onValueChange = { textView = it },
            label = { Text(text = man)}
        )
    })
}

@Preview
@Composable
fun EmojiCompoatScreenPreview() {
    EmojiCompoatScreen(navController = rememberNavController())
}