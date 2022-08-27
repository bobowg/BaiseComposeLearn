package com.example.baisecomposelearn.screens.media

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.screens.components.ScreenModel
import com.simonsickle.compose.barcodes.Barcode
import com.simonsickle.compose.barcodes.BarcodeType

@Composable
fun QrcodeScreen(navController: NavController) {
    val url = "https://github.com/bobowg/BaiseComposeLearn"
    ScreenModel(navController = navController, content = {
        if (BarcodeType.QR_CODE.isValueValid(url)) {
            Barcode(
                type = BarcodeType.QR_CODE,
                value = url,
                resolutionFactor = 10,
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .border(width = 4.dp, color = Color.Yellow),
                showProgress = true
            )
        }

        if (!BarcodeType.CODE_128.isValueValid(url)){
            Text(text = stringResource(id = R.string.qrcode,"这与代码 128 不兼容"))
        }
    })
}

@Preview
@Composable
fun QrcodeScreenPreview() {
    QrcodeScreen(navController = rememberNavController())
}