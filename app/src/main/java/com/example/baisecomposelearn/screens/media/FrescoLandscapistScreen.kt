package com.example.baisecomposelearn.screens.media

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.screens.components.ScreenModel
import com.example.baisecomposelearn.screens.components.imgUrl
import com.facebook.drawee.backends.pipeline.Fresco
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.fresco.FrescoImage

@Composable
fun FrescoLandscapistScreen(navController: NavController) {
    val context = LocalContext.current
    Fresco.initialize(context)
    ScreenModel(navController = navController, content = {
        FrescoImage(
            imageUrl = imgUrl(),
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop,
            circularReveal = CircularReveal(duration = 300),
            placeHolder = Icons.Filled.Image,
            error = Icons.Filled.Error
        )
        FrescoImage(
            imageUrl = imgUrl(),
            modifier = Modifier.fillMaxWidth(),
            loading = {
                ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                    val indicator = createRef()
                    CircularProgressIndicator(modifier = Modifier.constrainAs(indicator){
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    })
                }
            },
            failure = {
                Text(text = stringResource(id = R.string.frescolandscapist))
            }
        )
        FrescoImage(
            imageUrl = imgUrl(),
            modifier = Modifier.fillMaxWidth(),
            shimmerParams = ShimmerParams(
                baseColor = MaterialTheme.colors.background,
                highlightColor = Color.White,
                durationMillis = 300,
                dropOff = 0.65f,
                tilt = 20f
            ),
            failure = {
                Text(text = stringResource(id = R.string.frescolandscapist))
            }
        )
    })

}

@Preview
@Composable
fun FrescoLandscapistScreenPreview() {
    FrescoLandscapistScreen(navController = rememberNavController())
}