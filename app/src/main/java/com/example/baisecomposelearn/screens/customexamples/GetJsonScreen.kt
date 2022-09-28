package com.example.baisecomposelearn.screens.customexamples

import android.util.Log
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
import org.json.JSONObject
import org.json.JSONTokener
import java.net.URL
import javax.net.ssl.HttpsURLConnection

@Composable
fun GetJsonScreen(navController: NavController) {
    var issimple by remember { mutableStateOf(false) }
    var isnestedJSON by remember { mutableStateOf(false) }
    var isarrayJSON by remember { mutableStateOf(false) }
    ScreenModel(navController = navController, content = {
        if (issimple) {
            JsonCard()
        }
        if (isnestedJSON) {
            nestedCard()
        }
        Button(
            onClick = {
                issimple = true
                isnestedJSON = false
                isarrayJSON = false
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
        ) {
            Text(text = "简单JSON示例", fontSize = 24.sp)
        }
        Button(
            onClick = {
                issimple = false
                isnestedJSON = true
                isarrayJSON = false
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
        ) {
            Text(text = "非索引JSON示例", fontSize = 24.sp)
        }
        Button(
            onClick = {
                issimple = false
                isnestedJSON = false
                isarrayJSON = true
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
        ) {
            Text(text = "组数JSON示例", fontSize = 24.sp)
        }
    })
}

@Composable
fun nestedCard() {
    Box(modifier = Modifier.fillMaxSize()) {
        val url = URL("https://bobowg.pythonanywhere.com/static/img/nested.json")
        val httpsURLConnection = url.openConnection() as HttpsURLConnection
        httpsURLConnection.setRequestProperty("Accept", "application/json")
        httpsURLConnection.requestMethod = "GET"
        httpsURLConnection.doInput = true
        httpsURLConnection.doOutput = false
        val responseCode = httpsURLConnection.responseCode
        if (responseCode == HttpsURLConnection.HTTP_OK) {
            val response = httpsURLConnection.inputStream.bufferedReader().use { it.readText() }
            val gson = GsonBuilder().setPrettyPrinting().create()
            val prettyJson = gson.toJson(JsonParser.parseString(response))
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.Center)) {
                Text(
                    text = prettyJson,
                    modifier = Modifier
                        .background(color = Color.White),
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )
                val jsonObject = JSONTokener(response).nextValue() as JSONObject
                val jsonArray = jsonObject.getJSONArray("data")
                for (i in 0 until jsonArray.length()) {
                    Text(text = jsonArray.getJSONObject(i).getString("id") )
                    val employee = jsonArray.getJSONObject(i).getJSONObject("employee")
                    Text(text = employee.toString())
                    Text(text = employee.getString("name") )
                    val employeeSalary = employee.getJSONObject("salary")
                    val employeeSalaryUSD = employeeSalary.getInt("usd")
                    Text(text = employeeSalaryUSD.toString())
                    val employeeSalaryEUR = employeeSalary.getInt("eur")
                    Text(text = employeeSalaryEUR.toString())
                    val employeeAge = employee.getString("age")
                    Text(text = employeeAge)
                }
            }
        }else{
            Log.e("HTTPURLCONNECTION_ERROR", responseCode.toString())
        }
    }
}

@Composable
fun JsonCard() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
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
                    text = prettyJson,
                    modifier = Modifier
                        .background(color = Color.White)
                        .align(
                            Alignment.CenterHorizontally
                        ),
                    textAlign = TextAlign.Center,
                    color = Color.Black
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
