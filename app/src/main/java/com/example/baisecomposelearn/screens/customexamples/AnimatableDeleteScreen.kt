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
        "banafsdfena1",
        "ciwfewfewi1",
        "dpplffge1",
        "eangreeana1",
        "fgjhguiwi1",
        "gpplgregree1",
        "hangregeana1",
        "iiwgregrei1",
        "jpplessgr1",
        "kananrgega1",
        "liwggi1",
        "mpplgreee1",
        "nanansgrea1",
        "oiwgfdtretgi1",
        "pplsrre1",
        "qanangregesa1",
        "riwgretyredsi1",
        "spplgreee1",
        "tanangregesa1",
        "uiwfsei1",
        "vpjyjtple1",
        "wnanjtyra1",
        "hihtrrwi1",
        "ypphtrhrtle1",
        "zanadsytna1",
        "kiwhtrhri1",
        "apphtrhrle1",
        "banhtrhrana1",
        "cikjkhwi1",
        "dpliuiuple1",
        "eankuyikyana1",
        "fkuykuyiwi1",
        "gphkdple1",
        "hanagrregena1",
        "iiwgfdgsi1",
        "jppgresale1",
        "kangregana1",
        "ligregerwi1",
        "mpplgreese1",
        "nafsdfenana1",
        "oiwfwai1",
        "pplfewfwee1",
        "qanafwergrena1",
        "rgregriwi1",
        "spdgfdple1",
        "tangjyujyana1",
        "uiwZSdsai1",
        "vfewrwpple1",
        "wnanagrht1",
        "hiwgerei1",
        "yppgregele1",
        "zanagregena1",
        "kiwgdgdsi1",
        "apsgreple2",
        "bagrehgernana3",
        "cigregwi4",
        "dpple5",
        "eangfdgdana6",
        "fgdfgiwi2",
        "gphjhple2",
        "hanaasdana2",
        "iiwfewfwei2",
        "jpplfwexgae2",
        "kanafsafena2",
        "lifewwi2",
        "mppfsdfsle2",
        "nanfewg5reana2",
        "oidgrcfhwi2",
        "pphgfddle2",
        "qasgrenana2",
        "riwgergesi2",
        "sppgregele2",
        "tanasgrna2",
        "uigdrfhwi2",
        "vpgredple2",
        "wngdsana2",
        "higdfgswi2",
        "ypplgdfgfde2",
        "zanangregesa2",
        "kiwgreei2"
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