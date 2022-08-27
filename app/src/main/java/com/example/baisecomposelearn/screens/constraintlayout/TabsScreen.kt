package com.example.baisecomposelearn.screens.constraintlayout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.appdrawer.DefaultTopAppBar
import com.example.baisecomposelearn.model.TabItem
import com.example.baisecomposelearn.model.tabs
import com.example.baisecomposelearn.theme.kanit
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch


@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabsScreen(navController: NavController) {
    val pagerState = rememberPagerState()
    Scaffold(
        topBar = {
            DefaultTopAppBar(
                navController = navController,
                title = stringResource(id = R.string.app_name)
            )
        },
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            Tabs(tabs = tabs, pagerState = pagerState)
            TabContent(tabs = tabs, pagerState = pagerState)
        }
    }
}

@Preview
@Composable
fun TabsScreenPrview() {
    TabsScreen(navController = rememberNavController())
}



@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabContent(tabs: List<TabItem>, pagerState: PagerState) {
    HorizontalPager(state = pagerState, count = tabs.size) { page ->
        tabs[page].screen()
    }
}
@OptIn(ExperimentalPagerApi::class)
@Preview
@Composable
fun TabContentPreview() {
    TabContent(
        tabs = listOf(TabItem.Music, TabItem.Movie, TabItem.Book),
        pagerState = rememberPagerState()
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs(
    tabs: List<TabItem>, pagerState: PagerState
) {
    val scope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = colorResource(id = R.color.colorPrimaryDark),
        contentColor = colorResource(id = R.color.white),
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        }
    ) {
        tabs.forEachIndexed { index, tab ->
            LeadingIconTab(
                selected = pagerState.currentPage == index,
                onClick = { scope.launch { pagerState.animateScrollToPage(index) } },
                text = { Text(text = tab.title) },
                icon = {
                    Icon(
                        painter = painterResource(id = tab.icon),
                        contentDescription = ""
                    )
                }
            )
        }

    }

}

@OptIn(ExperimentalPagerApi::class)
@Preview(showBackground = true)
@Composable
fun TabsPreview() {
    Tabs(
        tabs = listOf(TabItem.Music, TabItem.Movie, TabItem.Book),
        pagerState = rememberPagerState()
    )

}

@Composable
fun MusicScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.colorPrimary))
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = stringResource(id = R.string.music),
            fontFamily = kanit,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )
    }
}

@Preview
@Composable
fun MusicScreenPreview() {
    MusicScreen()
}

@Composable
fun MoviesScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.colorPrimary))
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = stringResource(id = R.string.movie),
            fontFamily = kanit,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )
    }
}

@Preview
@Composable
fun MoviesScreenPreview() {
    MoviesScreen()
}

@Composable
fun BooksScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.colorPrimary))
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = stringResource(id = R.string.books),
            fontFamily = kanit,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BooksScreenPreview() {
    BooksScreen()
}