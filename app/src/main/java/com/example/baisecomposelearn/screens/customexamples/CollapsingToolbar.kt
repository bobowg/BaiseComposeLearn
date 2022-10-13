package com.example.baisecomposelearn.screens.customexamples

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.example.baisecomposelearn.R


private val headerHeight = 275.dp
private val toolbarHeight = 56.dp
private val paddingMedium = 0.dp
private val titlePaddingStart = 0.dp
private val titlePaddingEnd = 0.dp
private const val titleFontScaleStart = 1f
private const val titleFontScaleEnd = 0.66f

@Composable
fun CollapsingToolbar() {
    val scroll: ScrollState = rememberScrollState(0)

    Box(modifier = Modifier.fillMaxSize()) {
        Header(scroll = scroll, headerHeghtPx = 3f)
        Body(scroll = scroll)
        Toolbar(scroll = scroll, headerHeightPx = 1f, toolbarHeightPx = 1f)
        Title(scroll = scroll, headerHeightPx = 3f, toolbarHeightPx = 0f)
    }
}

@Composable
private fun Title(scroll: ScrollState,headerHeightPx: Float,toolbarHeightPx: Float) {
    var titleHeightPx by remember { mutableStateOf(0f) }
    val titleHeightDp = with(LocalDensity.current) { titleHeightPx.toDp() }
    val collapseRange: Float = (headerHeightPx - toolbarHeightPx)
    val collapseFraction: Float = (scroll.value / collapseRange).coerceIn(0f, 1f)

    val titleYFirstInterpolatedPoint = lerp(
        headerHeight - titleHeightDp - paddingMedium,
        headerHeight / 2,
        collapseFraction
    )
    val titleYSecondInterpolatedPoint = lerp(
        headerHeight / 2,
        toolbarHeight / 2 - titleHeightDp / 2,
        collapseFraction
    )
    val titleXFirstInterpolatedPoint = lerp(
        titlePaddingStart,
        titlePaddingEnd * 5 / 4,
        collapseFraction
    )
    val titleXSecondInterpolatedPoint = lerp(
        titlePaddingEnd * 5 / 4,
        titlePaddingEnd,
        collapseFraction
    )
    val titleY = lerp(
        titleYFirstInterpolatedPoint,
        titleYSecondInterpolatedPoint,
        collapseFraction
    )
    val titleX = lerp(
        titleXFirstInterpolatedPoint,
        titleXSecondInterpolatedPoint,
        collapseFraction
    )
    val scaleXY = lerp(
        titleFontScaleStart.dp,
        titleFontScaleEnd.dp,
        collapseFraction
    )
    Text(
        text = "New York",
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colors.onPrimary,
        modifier = Modifier
            .graphicsLayer {

                scaleX = scaleXY.value
                scaleY = scaleXY.value
                translationY = titleY.toPx()
                translationX = titleX.toPx()+ 120
            }
            .onGloballyPositioned {
                titleHeightPx = it.size.height.toFloat()
            }
    )
}


@Preview
@Composable
fun TitlePreview() {
    Title(scroll = rememberScrollState(),3f,1f)
}

@Composable
private fun Toolbar(scroll: ScrollState, headerHeightPx: Float, toolbarHeightPx: Float) {
    val toolbarBottom = headerHeightPx - toolbarHeightPx
    val showToolbar by remember {
        derivedStateOf { scroll.value >= toolbarBottom }
    }
    AnimatedVisibility(
        visible = showToolbar,
        enter = fadeIn(animationSpec = tween(300)),
        exit = fadeOut(animationSpec = tween(300))
    ) {


        TopAppBar(
            modifier = Modifier.background(
                brush = Brush.horizontalGradient(
                    listOf(Color(0xff026586), Color(0xff032C45))
                )
            ),
            navigationIcon = {
                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .size(36.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "",
                        tint = Color.White
                    )
                }
            },
            title = {},
            backgroundColor = Color.Transparent,
            elevation = 0.dp
        )
    }
}

@Preview
@Composable
fun ToolbarPreview() {
    Toolbar(scroll = rememberScrollState(), headerHeightPx = 3f, toolbarHeightPx = 1f)
}

@Composable
private fun Body(scroll: ScrollState) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.verticalScroll(scroll)
    ) {
        Spacer(modifier = Modifier.height(headerHeight))
        repeat(5) {
            Text(
                text = stringResource(id = R.string.content),
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .background(Color(0XFF161616))
                    .padding(16.dp)
            )
        }
    }
}

@Preview
@Composable
fun BodyPreview() {
    Body(scroll = rememberScrollState(0))
}


@Composable
private fun Header(scroll: ScrollState, headerHeghtPx: Float) {
    val headerHeightPx = with(LocalDensity.current) {
        headerHeight.toPx()
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(headerHeight)
            .graphicsLayer {
                translationY = -scroll.value.toFloat() / 2f
                alpha = (-1f / headerHeghtPx) * scroll.value + 1
            }
    ) {
        Image(
            painter = painterResource(id = R.drawable.newyork),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color(0xAA000000)),
                        startY = 3 * headerHeightPx / 4
                    )
                )
        )
    }
}

@Preview
@Composable
fun HearPreview() {
    Header(scroll = rememberScrollState(0), headerHeghtPx = 3f)
}
