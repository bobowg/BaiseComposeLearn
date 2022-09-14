package com.example.baisecomposelearn.screens.media

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.screens.components.DataPoints
import com.example.baisecomposelearn.screens.components.ScreenModel
import com.example.baisecomposelearn.theme.kanit
import com.example.baisecomposelearn.theme.rwOrgen
import com.example.baisecomposelearn.theme.rwRed
import com.madrapps.plot.line.DataPoint
import com.madrapps.plot.line.LineGraph
import com.madrapps.plot.line.LinePlot

@Composable
fun PlotScreen(navController: NavController) {
    ScreenModel(navController = navController, content = {
        PlotSampleGraph(lines = listOf(DataPoints.dataPoints1, DataPoints.dataPoints2))
        PlotSampleGraph1(lines = listOf(DataPoints.dataPoints1, DataPoints.dataPoints3))
    })
}

@Composable
fun PlotSampleGraph(lines: List<List<DataPoint>>) {
    LineGraph(
        plot = LinePlot(
            listOf(
                LinePlot.Line(
                    lines[0],
                    LinePlot.Connection(rwOrgen, 2.dp),
                    LinePlot.Intersection(rwOrgen, 5.dp),
                    LinePlot.Highlight(rwRed, 5.dp),
                    LinePlot.AreaUnderLine(rwRed, 0.3f)
                ),
                LinePlot.Line(
                    lines[1],
                    LinePlot.Connection(Color.Gray, 2.dp),
                    LinePlot.Intersection { center, _ ->
                        val px = 2.dp.toPx()
                        val topLeft = Offset(center.x - px, center.y - px)
                        drawRect(Color.Gray, topLeft, Size(px * 2, px * 2))
                    },
                ),
            ),
            selection = LinePlot.Selection(
                highlight = LinePlot.Connection(
                    rwRed,
                    strokeWidth = 2.dp,
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(40f, 20f))
                )
            ),
        ),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(200.dp)
    )
    Text(
        text = stringResource(id = R.string.plotscreen, "一"),
        color = MaterialTheme.colors.onPrimary,
        fontFamily = kanit,
        fontSize = 24.sp
    )
}

@Composable
fun PlotSampleGraph1(lines: List<List<DataPoint>>) {
    LineGraph(
        plot = LinePlot(
            listOf(
                LinePlot.Line(
                    lines[1],
                    LinePlot.Connection(Color.Gray, 2.dp),
                    null,
                    LinePlot.Highlight { center ->
                        val color = Color.Gray
                        drawCircle(color, 9.dp.toPx(), center, alpha = 0.3f)
                        drawCircle(color, 6.dp.toPx(), center)
                        drawCircle(Color.White, 3.dp.toPx(), center)
                    },
                ),
                LinePlot.Line(
                    lines[0],
                    LinePlot.Connection(Color.Blue, 3.dp),
                    LinePlot.Intersection(Color.Blue, 6.dp) { center, point ->
                        val x = point.x
                        val rad = if (x % 4f == 0f) 6.dp else 3.dp
                        drawCircle(
                            Color.Blue,
                            rad.toPx(),
                            center,
                        )
                    },
                    LinePlot.Highlight { center ->
                        val color = Color.Blue
                        drawCircle(color, 9.dp.toPx(), center, alpha = 0.3f)
                        drawCircle(color, 6.dp.toPx(), center)
                        drawCircle(Color.White, 3.dp.toPx(), center)
                    },
                    LinePlot.AreaUnderLine(Color.Blue, 0.1f)
                ),
            ), LinePlot.Grid(Color.Gray), paddingRight = 16.dp
        ),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(200.dp)
    )
    Text(
        text = stringResource(id = R.string.plotscreen, "二"),
        color = MaterialTheme.colors.onPrimary,
        fontFamily = kanit,
        fontSize = 24.sp
    )
}

@Preview
@Composable
fun PlotScreenPreview() {
    PlotScreen(navController = rememberNavController())
}