package com.example.baisecomposelearn.screens.components

import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import org.json.JSONObject
import org.json.JSONTokener
import java.net.URL
import javax.net.ssl.HttpsURLConnection


internal fun randomSampleImageUrl(
    seed: Int = ((Math.random() * 9 + 1) * 1000000).toInt(),
    width: Int = 300,
): String {
//    val url = URL("https://shibe.online/api/shibes?count=10&urls=true&httpsUrls=true")
    val url = URL("https://img.xjh.me/random_img.php?return=json")
    val httpsURLConnection = url.openConnection() as HttpsURLConnection
    httpsURLConnection.setRequestProperty("Accept", "application/json")
    httpsURLConnection.requestMethod = "GET"
    httpsURLConnection.doInput = true
    httpsURLConnection.doOutput = false
    val responseCode = httpsURLConnection.responseCode
    return if (responseCode == HttpsURLConnection.HTTP_OK) {
        val response =
            httpsURLConnection.inputStream.bufferedReader().use { it.readText() }
        val gson = GsonBuilder().setPrettyPrinting().create()
        val prettyJson = gson.toJson(JsonParser.parseString(response))
        val jsonObject = JSONTokener(prettyJson).nextValue() as JSONObject
        val img = "https:" + jsonObject.getString("img")
        img
    } else {
        "https://images.pexels.com/photos/$seed/pexels-photo-$seed.jpeg?auto=compress&cs=tinysrgb&h=$width"
    }
}
