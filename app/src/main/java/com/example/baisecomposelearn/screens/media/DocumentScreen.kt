package com.example.baisecomposelearn.screens.media

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.screens.components.ScreenModel
import com.zachklipp.richtext.ui.printing.Printable
import com.zachklipp.richtext.ui.printing.rememberPrintableController
import com.zachklipp.richtext.ui.printing.responsivePadding

@Composable
fun DocumentScreen(navController: NavController) {
    val printableController = rememberPrintableController()
    val document = stringResource(id = R.string.document)
    ScreenModel(navController = navController, content = {
        Printable(
            controller = printableController, pageDpi = 96, modifier = Modifier.responsivePadding(
                600.dp to 32.dp,
                Dp.Infinity to 96.dp
            )
        ) {
            Column {
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.h3,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.size(24.sp))
                for (i in 1..10) {
                    SectionWithLoremIpsum(i)
                }
            }
        }

    })
}

@Composable
fun SectionWithLoremIpsum(sectionNumber: Int) {
    Text(
        text = "Section #$sectionNumber",
        style = MaterialTheme.typography.h6,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(horizontal = 16.dp)
    )
    Text(
        text = stringResource(id = R.string.lorem_ipsum),
        style = MaterialTheme.typography.h6,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(horizontal = 16.dp)
    )

}

@Preview
@Composable
fun DocumentScreenPreview() {
    DocumentScreen(navController = rememberNavController())
}