package com.example.baisecomposelearn.screens.constraintlayout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.screens.components.ScreenModel

@Composable
fun JsonConstraintScreen(navController: NavController) {
    ScreenModel(navController = navController, content = {
        JsonConstraintSet()
        Spacer(modifier = Modifier.height(130.dp))
    })
}

@Composable
fun JsonConstraintSet() {
    ConstraintLayout(
        ConstraintSet(
            """
            {
                Header: { exportAs: 'json contraint set example'},
                g1: { type: 'vGuideline', start: 80 },
                g2: { type: 'vGuideline', end: 80 },
                button1: {
                  width: 'spread',
                  top: ['title', 'bottom', 16],
                  start: ['g1', 'start'],
                  end: ['g2', 'end']
                },
                text1: {
                  width: { value: 'wrap', max: 300 },
                  centerVertically: 'parent',
                  start: ['g1', 'start'],
                  end: ['g2','end']
                }
            }
        """
        ),
        modifier = Modifier.fillMaxSize()
    ) {
        Button(
            modifier = Modifier.layoutId("button1"),
            onClick = {},
        ) {
            Text(text = stringResource(id = R.string.jsonconstraint))
        }
        Text(
            modifier = Modifier.padding(top = 106.dp)
                .layoutId("text1")
                .background(Color.Red),
            text = stringResource(id = R.string.jsonconstraint),
            style = MaterialTheme.typography.body1,
        )
    }
}

@Preview
@Composable
fun JsonConstraintScreenPreview() {
    JsonConstraintScreen(navController = rememberNavController())
}