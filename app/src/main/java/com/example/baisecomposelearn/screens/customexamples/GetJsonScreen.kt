package com.example.baisecomposelearn.screens.customexamples

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.screens.components.ScreenModel
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.coroutines.DelicateCoroutinesApi
import org.json.JSONObject
import org.json.JSONTokener
import java.net.URL
import javax.net.ssl.HttpsURLConnection

@Composable
fun GetJsonScreen(navController: NavController) {
    var isopen by remember { mutableStateOf(false) }
    ScreenModel(navController = navController, content = {
        if (isopen) {
            JsonCard()
        }
        Button(
            onClick = {
                isopen = true

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
        ) {
            Text(text = "简单JSON示例", fontSize = 24.sp)
        }
    })
}

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(DelicateCoroutinesApi::class)
@Composable
fun JsonCard() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column (modifier = Modifier.fillMaxWidth().padding(16.dp)){
            val url = URL("https://bobowg.pythonanywhere.com/static/img/simplae.json")
            val httpsURLConnection = url.openConnection() as HttpsURLConnection
            httpsURLConnection.setRequestProperty("Accept", "application/json")
            httpsURLConnection.requestMethod = "GET"
            httpsURLConnection.doInput = true
            httpsURLConnection.doOutput = false
            val responseCode = httpsURLConnection.responseCode
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                val response =
                    httpsURLConnection.inputStream.bufferedReader().use { it.readText() }
                val gson = GsonBuilder().setPrettyPrinting().create()
                val prettyJson = gson.toJson(JsonParser.parseString(response))
                Text(
                    text = prettyJson, modifier = Modifier
                        .background(color = Color.White)
                        .align(
                            Alignment.CenterHorizontally
                        ),
                    textAlign = TextAlign.Center
                )
                val jsonObject = JSONTokener(response).nextValue() as JSONObject
                val id = jsonObject.getString("id")
                Text(
                    text = "id:${id}", style = TextStyle(
                        color = MaterialTheme.colors.onPrimary,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )
                )
                val employeeName = jsonObject.getString("employee_name")
                Text(
                    text = "employeeName:${employeeName}", style = TextStyle(
                        color = MaterialTheme.colors.onPrimary,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )
                )
                val employeeSalary = jsonObject.getString("employee_salary")
                Text(
                    text = "employeeSalary:${employeeSalary}", style = TextStyle(
                        color = MaterialTheme.colors.onPrimary,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )
                )
                val employeeAge = jsonObject.getString("employee_age")
                Text(
                    text = "employeeAge:${employeeAge}", style = TextStyle(
                        color = MaterialTheme.colors.onPrimary,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun GetJsonScreenPreview() {
    GetJsonScreen(navController = rememberNavController())
}
