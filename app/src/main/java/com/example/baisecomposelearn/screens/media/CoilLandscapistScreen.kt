package com.example.baisecomposelearn.screens.media

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.screens.components.goBack
import com.example.baisecomposelearn.screens.components.randomSampleImageUrl
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun CoilLandscapistScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CoilImage(
            imageModel = randomSampleImageUrl(),
            modifier = Modifier.fillMaxWidth(),
            circularReveal = CircularReveal(duration = 300),
            placeHolder = Icons.Filled.Image,
            error = Icons.Filled.Error
        )
        Divider(modifier = Modifier.height(2.dp))
        CoilImage(
            imageModel = randomSampleImageUrl(),
            modifier = Modifier.fillMaxWidth(),
            loading = {
                ConstraintLayout(
                    modifier = Modifier.fillMaxSize()
                ) {
                    val indicator = createRef()
                    CircularProgressIndicator(
                        modifier = Modifier.constrainAs(indicator) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                    )
                }
            },
            failure = {
                Text(text = stringResource(id = R.string.coilimage,""))
            })
        Divider(modifier = Modifier.height(2.dp))
        CoilImage(
            imageModel = randomSampleImageUrl(),
            modifier = Modifier.fillMaxWidth(),
            shimmerParams = ShimmerParams(
                baseColor = MaterialTheme.colors.background,
                highlightColor = Color.White,
                durationMillis = 350,
                dropOff = 0.65f,
                tilt = 20f
            ),
            failure = {
                Text(text = stringResource(id = R.string.coilimage))
            })
        goBack(navController = navController)

    }
}