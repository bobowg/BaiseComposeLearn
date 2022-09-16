package com.example.baisecomposelearn.screens.customexamples

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.screens.components.goBack
import com.example.baisecomposelearn.theme.colors
import com.example.baisecomposelearn.theme.pacifico
import com.example.baisecomposelearn.utils.capitalizeFirstLetter

@Composable
fun AnimatableDeleteScreen(navController: NavController) {
    AnimatableDeleteExplame(routes = routeList, navController = navController)
}

internal val routeList
    get():List<String> = listOf(
        "apple",
        "banana",
        "ciwi",
        "dpple",
        "eanana",
        "fiwi",
        "gpple",
        "hanana",
        "iiwi",
        "jpple",
        "kanana",
        "liwi",
        "mpple",
        "nanana",
        "oiwi",
        "pple",
        "qanana",
        "riwi",
        "spple",
        "tanana",
        "uiwi",
        "vpple",
        "wnana",
        "hiwi",
        "ypple",
        "zanana",
        "kiwi",
        "apple1",
        "banana1",
        "ciwi1",
        "dpple1",
        "eanana1",
        "fiwi1",
        "gpple1",
        "hanana1",
        "iiwi1",
        "jpple1",
        "kanana1",
        "liwi1",
        "mpple1",
        "nanana1",
        "oiwi1",
        "pple1",
        "qanana1",
        "riwi1",
        "spple1",
        "tanana1",
        "uiwi1",
        "vpple1",
        "wnana1",
        "hiwi1",
        "ypple1",
        "zanana1",
        "kiwi1",
        "apple1",
        "banana1",
        "ciwi1",
        "dpple1",
        "eanana1",
        "fiwi1",
        "gpple1",
        "hanana1",
        "iiwi1",
        "jpple1",
        "kanana1",
        "liwi1",
        "mpple1",
        "nanana1",
        "oiwi1",
        "pple1",
        "qanana1",
        "riwi1",
        "spple1",
        "tanana1",
        "uiwi1",
        "vpple1",
        "wnana1",
        "hiwi1",
        "ypple1",
        "zanana1",
        "kiwi1",
        "apple2",
        "banana3",
        "ciwi4",
        "dpple5",
        "eanana6",
        "fiwi2",
        "gpple2",
        "hanana2",
        "iiwi2",
        "jpple2",
        "kanana2",
        "liwi2",
        "mpple2",
        "nanana2",
        "oiwi2",
        "pple2",
        "qanana2",
        "riwi2",
        "spple2",
        "tanana2",
        "uiwi2",
        "vpple2",
        "wnana2",
        "hiwi2",
        "ypple2",
        "zanana2",
        "kiwi2"
    ).sorted()

@Composable
private fun AnimatableDeleteExplame(routes: List<String>, navController: NavController) {
    val deleteRouteList = remember { mutableStateListOf<String>() }
    LazyColumn(contentPadding = PaddingValues(16.dp)) {
        itemsIndexed(routes) { index, route ->
            AnimatedVisibility(
                visible = !deleteRouteList.contains(route),
                enter = expandVertically(),
                exit = shrinkHorizontally(animationSpec = tween(durationMillis = 1000))
            ) {
                CardExample(index, route, Modifier.fillParentMaxWidth()) {
                    IconButton(onClick = { deleteRouteList.add(route) }) {
                        Icon(
                            imageVector = Icons.Filled.Delete, contentDescription = stringResource(
                                id = R.string.delete
                            )
                        )
                 }
                }
            }
        }
        item { 
            goBack(navController = navController)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CardExample(
    index: Int,
    route: String,
    modifier: Modifier = Modifier,
    trailing: @Composable (() -> Unit)? = null
) {
    Card(
        shape = RoundedCornerShape(4.dp),
        modifier = modifier.padding(vertical = 8.dp),
        backgroundColor = MaterialTheme.colors.primary
    ) {
        ListItem(
            icon = {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(Color(colors[index % colors.size]))
                ) {
                    Text(
                        text = route.first().uppercaseChar().toString(),
                        fontSize = 20.sp,
                        color = MaterialTheme.colors.onSurface,
                        fontWeight = FontWeight.Bold,
                        fontFamily = pacifico,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            },
            trailing = trailing
        ) {
            Text(
                text = route.capitalizeFirstLetter(),
                fontFamily = pacifico,
                style = TextStyle(
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start
                )
            )
        }
    }
}

@Preview
@Composable
fun AnimatableScreenPreview() {
    AnimatableDeleteScreen(navController = rememberNavController())
}