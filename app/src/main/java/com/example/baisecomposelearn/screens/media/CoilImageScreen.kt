package com.example.baisecomposelearn.screens.media

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.screens.components.ScreenModel

@Composable
fun CoilImageScreen(navController: NavController) {
    ScreenModel(navController = navController, content = {
        val context = LocalContext.current
        AsyncImage(
            model = "https://i.pinimg.com/564x/2a/91/ca/2a91ca75ed9296ea93b23b189584b231.jpg",
            contentDescription = stringResource(id = R.string.coilimage)
        )
        Divider()
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data("https://i.pinimg.com/564x/2a/91/ca/2a91ca75ed9296ea93b23b189584b231.jpg")
                .crossfade(true).build(),
            contentDescription = stringResource(
                id = R.string.coilimage
            ),
            placeholder = painterResource(id = R.drawable.logo),
            error = painterResource(id = com.google.android.material.R.drawable.mtrl_ic_error),
            onSuccess = {
                Log.d(TAG,"success")
            }
        )
        Divider()
        SubcomposeAsyncImage(
            model = "https://i.pinimg.com/564x/2a/91/ca/2a91ca75ed9296ea93b23b189584b231.jpg",
            contentDescription = stringResource(R.string.coilimage)
        ) {
            if (painter.state is AsyncImagePainter.State.Loading || painter.state is AsyncImagePainter.State.Error) {
                CircularProgressIndicator()
            } else {
                SubcomposeAsyncImageContent()
            }
        }

    })
}
