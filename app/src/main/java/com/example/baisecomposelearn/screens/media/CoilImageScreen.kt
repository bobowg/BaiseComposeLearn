package com.example.baisecomposelearn.screens.media

import android.content.ContentValues.TAG
import android.net.Uri
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.example.baisecomposelearn.screens.components.randomSampleImageUrl
import timber.log.Timber

@Composable
fun CoilImageScreen(navController: NavController) {
    ScreenModel(navController = navController, content = {
        val context = LocalContext.current
        AsyncImage(
            modifier = Modifier.fillMaxWidth(),
            model = randomSampleImageUrl(),
            contentDescription = stringResource(id = R.string.coilimage,"")
        )
        Divider()
        AsyncImage(
            modifier = Modifier.fillMaxWidth(),
            model = ImageRequest.Builder(context)
                .data(randomSampleImageUrl())
                .crossfade(true).build(),
            contentDescription = stringResource(
                id = R.string.coilimage,""
            ),
            placeholder = painterResource(id = R.drawable.logo),
            error = painterResource(id = com.google.android.material.R.drawable.mtrl_ic_error),
            onSuccess = {
                Timber.tag(TAG).d("success")
            }
        )
        Divider()
        SubcomposeAsyncImage(
            model = Uri.parse(randomSampleImageUrl()),
            contentDescription = stringResource(R.string.coilimage,""),
            modifier = Modifier.fillMaxWidth().clip(CircleShape)
        ) {
            if (painter.state is AsyncImagePainter.State.Loading || painter.state is AsyncImagePainter.State.Error) {
                CircularProgressIndicator()
            } else {
                SubcomposeAsyncImageContent()
            }
        }

    })
}

