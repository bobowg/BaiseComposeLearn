package com.example.baisecomposelearn.appdrawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.navitegation.NavitemScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun TopAppBar(
    scope: CoroutineScope,
    scoffoldState: ScaffoldState
) {


    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.app_name), fontSize = 18.sp)
        },
        navigationIcon = {
            IconButton(onClick = { scope.launch { scoffoldState.drawerState.open() } }) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "open")
            }
        },
        backgroundColor = colorResource(id = R.color.colorPrimary),
        contentColor = Color.White,
    )
}

@Preview
@Composable
fun AppBarPreview() {
    val scope = rememberCoroutineScope()
    val scoffoldState = rememberScaffoldState()
    TopAppBar(scope, scoffoldState)
}

@Composable
fun Drawer(
    scope: CoroutineScope,
    scoffoldState: ScaffoldState,
    navController: NavController
) {
    val items = listOf(
        NavitemScreen.Activate,
        NavitemScreen.Animation,
        NavitemScreen.ConstraintLayoutScreen,
        NavitemScreen.ViewModelScreen,
    )
    Column(modifier = Modifier.background(colorResource(id = R.color.colorPrimary))) {
        Row {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = stringResource(
                    id = R.string.app_name
                ),
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp)
                    .size(50.dp)
            )
            Text(
                text = stringResource(id = R.string.app_name),
                color = colorResource(id = R.color.white),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(start = 26.dp, top = 16.dp)
                    .align(Alignment.CenterVertically)
            )
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.dp)
        )
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            DrawerItem(item = item,
                selected = currentRoute == item.route,
                onItemClick = {
                    navController.navigate(item.route)
                    scope.launch {
                        scoffoldState.drawerState.close()
                    }
            })
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = stringResource(id = R.string.app_name),
            color = Color.White,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(12.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}

@Preview
@Composable
fun DrawerPreview() {
    val scope = rememberCoroutineScope()
    val scoffoldState = rememberScaffoldState()
    val navController = rememberNavController()
    Drawer(scope = scope, scoffoldState = scoffoldState, navController = navController)
}

@Composable
fun DrawerItem(
    item: NavitemScreen,
    selected: Boolean,
    onItemClick: (NavitemScreen) -> Unit
) {
    val background = if (selected) R.color.colorPrimaryDark else R.color.colorPrimary
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { onItemClick(item) })
            .height(45.dp)
            .background(colorResource(id = background))
            .padding(start = 10.dp)
    ) {
        Text(
            text = stringResource(id = item.title),
            fontSize = 18.sp,
            color = Color.White,
            modifier = Modifier
                .padding(start = 16.dp, bottom = 16.dp)
        )
    }

}

@Preview
@Composable
fun DrawerItemPreview() {
    DrawerItem(item = NavitemScreen.Activate, selected = false, onItemClick = {})
}