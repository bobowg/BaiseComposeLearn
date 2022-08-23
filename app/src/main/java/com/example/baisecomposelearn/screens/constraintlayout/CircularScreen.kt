package com.example.baisecomposelearn.screens.constraintlayout

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.screens.components.ScreenModel

@Composable
fun CircularScreen(
    navController: NavController
) {
    ScreenModel(navController = navController, content = {
        CircularExplame()
        Spacer(
            modifier = Modifier
                .height(250.dp)
                .padding(bottom = 16.dp)
        )
    })
}

@Composable
fun CircularExplame() {
    ConstraintLayout {
        val (fab1, fab2, fab3, fab4, fab5, fab6) = createRefs()
        FloatingActionButton(onClick = { /*TODO*/ }, modifier = Modifier.constrainAs(fab1) {
            centerHorizontallyTo(parent)
            centerVerticallyTo(parent)
        }) {
            Icon(imageVector = Icons.Default.Menu, contentDescription = null)
        }
        FloatingActionButton(onClick = { /*TODO*/ }, modifier = Modifier.constrainAs(fab2) {
            circular(fab1,72.0f,100.dp)
        }) {
            Icon(imageVector = Icons.Filled.MenuOpen, contentDescription = null)
        }
        FloatingActionButton(onClick = { /*TODO*/ }, modifier = Modifier.constrainAs(fab3) {
            circular(fab1,144.0f,100.dp)
        }) {
            Icon(imageVector = Icons.Filled.MenuBook, contentDescription = null)
        }
        FloatingActionButton(onClick = { /*TODO*/ }, modifier = Modifier.constrainAs(fab4) {
            circular(fab1,216.0f,100.dp)
        }) {
            Icon(imageVector = Icons.Filled.RestaurantMenu, contentDescription = null)
        }
        FloatingActionButton(onClick = { /*TODO*/ }, modifier = Modifier.constrainAs(fab5) {
            circular(fab1,288.0f,100.dp)
        }) {
            Icon(imageVector = Icons.Filled.AddAlarm, contentDescription = null)
        }
        FloatingActionButton(onClick = { /*TODO*/ }, modifier = Modifier.constrainAs(fab6) {
            circular(fab1,360.0f,100.dp)
        }) {
            Icon(imageVector = Icons.Filled.AddBox, contentDescription = null)
        }
    }

}

@Preview
@Composable
fun CircularScreenPreview() {
    CircularScreen(navController = rememberNavController())
}