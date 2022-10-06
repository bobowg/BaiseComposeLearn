package com.example.baisecomposelearn.screens.customexamples


import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.screens.components.ScreenModel

@Composable
fun EmojiCompoatScreen(navController: NavController) {
    val man = "\uD83D\uDC69\u200D\uD83C\uDFA4"
    var textView by remember { mutableStateOf("") }
    textView =
        stringResource(id = R.string.emojicompoat) + "\uD83D\uDC69\u200D\uD83D\uDCBB" + man
    ScreenModel(navController = navController, content = {
        Text(
            text = stringResource(id = R.string.emojicompoat) + man , fontSize = 28.sp,
            color = MaterialTheme.colors.onPrimary
        )
        Spacer(modifier = Modifier.height(14.dp))
        TextField(
            value = textView,
            onValueChange = { textView = it },
            label = { Text(text = man) },
            placeholder = {
                Text(text = man, color = Color.Blue)
            },
            singleLine = true,
            maxLines = 12,
            keyboardOptions = KeyboardOptions.Default,

            )
        Spacer(modifier = Modifier.height(14.dp))
        Button(onClick = { /*TODO*/ }) {
            Text(
                text = stringResource(id = R.string.emojicompoat) + man,
                fontSize = 28.sp,
                color = MaterialTheme.colors.onPrimary
            )
        }
    })
}

@Preview
@Composable
fun EmojiCompoatScreenPreview() {
    EmojiCompoatScreen(navController = rememberNavController())
}