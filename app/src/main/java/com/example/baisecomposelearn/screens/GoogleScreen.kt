package com.example.baisecomposelearn.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.screens.components.goBack

@Composable
fun googleMap(navController: NavController) {
    val Context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.colorPrimary))
            .wrapContentSize(Alignment.Center),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                // Create a Uri from an intent string. Use the result to create an Intent.
                val gmmIntentUri = Uri.parse("google.streetview:cbll=46.414382,10.013988")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                startActivity(Context,mapIntent,null)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.googlemap),
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                color = Color.White
            )
        }

        goBack(navController = navController)
    }

}

@Preview
@Composable
fun googleMapPreview() {
    googleMap(navController = rememberNavController())
}