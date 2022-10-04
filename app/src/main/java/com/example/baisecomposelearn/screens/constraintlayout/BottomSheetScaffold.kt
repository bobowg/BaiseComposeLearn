package com.example.baisecomposelearn.screens.constraintlayout

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.appdrawer.DefaultTopAppBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetScaffoldScreen(navController: NavController) {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()
    BottomSheetScaffold(
        sheetContent = { BottomSheetContent() },
        scaffoldState = bottomSheetScaffoldState,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetBackgroundColor = colorResource(id = R.color.colorPrimary)
    ) {
        Scaffold(
            topBar = {
                DefaultTopAppBar(
                    navController = navController,
                    title = stringResource(id = R.string.bottomsheetscaffold)
                )
            },
            backgroundColor = colorResource(id = R.color.colorPrimaryDark)
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                mainScreen(scope = scope, state = bottomSheetScaffoldState)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun mainScreen(scope: CoroutineScope, state: BottomSheetScaffoldState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.colorPrimaryDark)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(
                    id = R.color.colorPrimary
                ),
                contentColor = Color.White
            ),
            onClick = {
                scope.launch {
                    if (state.bottomSheetState.isCollapsed) {
                        state.bottomSheetState.expand()
                    }else{
                        state.bottomSheetState.collapse()
                    }

                }
            },
        ) {
            if (state.bottomSheetState.isCollapsed){
                Text(text = stringResource(id = R.string.modalbottomsheetlayout,"打开"))
            }else{
                Text(text = stringResource(id = R.string.modalbottomsheetlayout,"关闭"))
            }
        }
    }
}

@Composable
fun BottomSheetListItem(icon: Int, title: String, onItemClick: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { onItemClick(title) })
            .height(55.dp)
            .background(color = colorResource(id = R.color.colorPrimary))
            .padding(start = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(painter = painterResource(id = icon), contentDescription = "Share", tint = Color.White)
        Spacer(modifier = Modifier.width(20.dp))
        Text(text = title, color = Color.White)
    }
}

@Preview(showBackground = true)
@Composable
fun BottomSheetListItemPreview() {
    BottomSheetListItem(icon = R.drawable.ic_share, title = "Share", onItemClick = { })
}

@Composable
fun BottomSheetContent() {
    val context = LocalContext.current
    Column {
        BottomSheetListItem(
            icon = R.drawable.ic_share,
            title = "Share",
            onItemClick = { title ->
                Toast.makeText(
                    context,
                    title,
                    Toast.LENGTH_SHORT
                ).show()
            })
        BottomSheetListItem(
            icon = R.drawable.ic_highlight,
            title = "Get link",
            onItemClick = { title ->
                Toast.makeText(
                    context,
                    title,
                    Toast.LENGTH_SHORT
                ).show()
            })
        BottomSheetListItem(
            icon = R.drawable.ic_book,
            title = "Book",
            onItemClick = { title ->
                Toast.makeText(
                    context,
                    title,
                    Toast.LENGTH_SHORT
                ).show()
            })
        BottomSheetListItem(
            icon = R.drawable.ic_music,
            title = "Misc",
            onItemClick = { title ->
                Toast.makeText(
                    context,
                    title,
                    Toast.LENGTH_SHORT
                ).show()
            })
    }
}

@Preview(showBackground = true)
@Composable
fun BottomSheetContentPreview() {
    BottomSheetContent()
}