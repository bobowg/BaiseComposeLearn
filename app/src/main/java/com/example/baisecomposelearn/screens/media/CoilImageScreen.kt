package com.example.baisecomposelearn.screens.media

import android.content.ContentValues.TAG
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
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
            model = imgUrl(),
            contentDescription = stringResource(id = R.string.coilimage)
        )
        Divider()
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(imgUrl())
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
            model = Uri.parse(imgUrl()),
            contentDescription = stringResource(R.string.coilimage),
            modifier = Modifier.clip(CircleShape)
        ) {
            if (painter.state is AsyncImagePainter.State.Loading || painter.state is AsyncImagePainter.State.Error) {
                CircularProgressIndicator()
            } else {
                SubcomposeAsyncImageContent()
            }
        }

    })
}

internal fun imgUrl():String = ImagePitrue.getImageUrl.random().getImgUrl
