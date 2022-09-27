package com.example.baisecomposelearn.screens.customexamples

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.screens.components.ScreenModel
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL
import javax.net.ssl.HttpsURLConnection

@Composable
fun GetJsonScreen(navController: NavController) {
    var isopen by remember{ mutableStateOf(false)}
    ScreenModel(navController = navController, content = {
        if (isopen){
            JsonCard()
        }
        Button(
            onClick = { isopen = true },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
        ) {
            Text(text = "简单JSON示例", fontSize = 24.sp)
        }
    })
}


@Composable
fun JsonCard() {
    val url =
        URL("https://github.com/bobowg/BaiseComposeLearn/blob/master/simple.json")
    val httpsUrlConntion = url.openConnection() as HttpsURLConnection
    httpsUrlConntion.setRequestProperty("Accept", "application/json")
    httpsUrlConntion.requestMethod = "GET"
    httpsUrlConntion.doInput = true
    httpsUrlConntion.doOutput = false
    val responseCode = httpsUrlConntion.responseCode
    if(responseCode == HttpsURLConnection.HTTP_OK){
        val response = httpsUrlConntion.inputStream.bufferedReader().use { it.readText() }
        val gson = GsonBuilder().setPrettyPrinting().create()
        val prettyJson = gson.toJson(JsonParser.parseString(response))
        Log.d("Pretty Printed JSON :", prettyJson)
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column {
                Text(text = prettyJson.toString())

            }
        }
    }

}

@Preview
@Composable
fun GetJsonScreenPreview() {
    GetJsonScreen(navController = rememberNavController())
}
