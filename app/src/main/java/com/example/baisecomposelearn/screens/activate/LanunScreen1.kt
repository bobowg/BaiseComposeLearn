package com.example.baisecomposelearn.screens.activate

import android.Manifest
import android.graphics.Bitmap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.screens.components.goBack
import com.example.baisecomposelearn.utils.isCameraPermissionGranted
import com.example.baisecomposelearn.utils.shortToast
import com.example.baisecomposelearn.utils.startActivitySafe
import timber.log.Timber

@Composable
fun LanunScreen1(navController: NavController) {
    val context = LocalContext.current
    val result = rememberSaveable { mutableStateOf<Bitmap?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) {
        result.value = it
    }
    val permissionLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                startActivitySafe(
                    startActivity = { launcher.launch() },
                    activityNotFoundAction = { e ->
                        Timber.e(e)
                        context.shortToast(R.string.activity_not_found)
                    }
                )
            } else {
                context.shortToast(R.string.permission_not_granted)
            }
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.colorPrimary))
            .wrapContentSize(Alignment.Center),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                if (context.isCameraPermissionGranted) {
                    launcher.launch()
                } else {
                    permissionLauncher.launch(Manifest.permission.CAMERA)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.launch1),
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                color = Color.White
            )
        }
        result.value?.let { image ->
            Image(
                bitmap = image.asImageBitmap(),
                contentDescription = null,
                modifier = Modifier.size(250.dp)
            )
        }

        goBack(navController = navController)
    }
}

@Preview
@Composable
private fun LanunScreenPreview() {
    LanunScreen1(navController = rememberNavController())
}