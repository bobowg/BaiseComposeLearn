package com.example.baisecomposelearn.screens.animate

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.navitegation.NavitemScreen
import com.example.baisecomposelearn.screens.components.ScreenModel

@Composable
fun AnimationScreen(navController: NavController) {
    val modo = Modifier
        .fillMaxWidth()
        .padding(16.dp)
    ScreenModel(
        navController = navController,
        isGoBack = false,
        content = {
            Button(modifier = modo,
                onClick = { navController.navigate(NavitemScreen.AnimatedScreen.route) }) {
                Text(
                    text = stringResource(id = R.string.animation1),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.White
                )
            }
            Button(modifier = modo,
                onClick = { navController.navigate(NavitemScreen.AnimationContentSizeScreen.route) }) {
                Text(
                    text = stringResource(id = R.string.animationcontentsize, "动画"),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.White
                )
            }
            Button(modifier = modo,
                onClick = { navController.navigate(NavitemScreen.CrossFadeScreen.route) }) {
                Text(
                    text = stringResource(id = R.string.crossfade, "动画"),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.White
                )
            }
            Button(modifier = modo,
                onClick = { navController.navigate(NavitemScreen.UpdateTransition.route) }) {
                Text(
                    text = stringResource(id = R.string.updatetransition, "动画项"),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.White
                )
            }
            Button(modifier = modo,
                onClick = { navController.navigate(NavitemScreen.AnimationSpecScreen.route) }) {
                Text(
                    text = stringResource(id = R.string.animationspec),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.White
                )
            }
            Button(modifier = modo,
                onClick = { navController.navigate(NavitemScreen.AnimatedVectorDrawableScreen.route) }) {
                Text(
                    text = stringResource(id = R.string.animatedvector),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.White
                )
            }
            Button(modifier = modo,
                onClick = {
                    navController.navigate(NavitemScreen.AnimatePlacementScreen.route)
                }) {
                Text(
                    text = stringResource(id = R.string.animateitemplacement),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.White
                )
            }
            Button(modifier = modo,
                onClick = {
                    navController.navigate(NavitemScreen.CurtainEffectScreen.route)
                }) {
                Text(
                    text = stringResource(id = R.string.curtaineffect),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.White
                )
            }
            Button(modifier = modo,
                onClick = {
                    navController.navigate(NavitemScreen.CircularProgressbar.route)
                }) {
                Text(
                    text = stringResource(id = R.string.animate),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.White
                )
            }
            Button(modifier = modo,
                onClick = {
                    navController.navigate(NavitemScreen.AnimateColorComponent.route)
                }) {
                Text(
                    text = stringResource(id = R.string.animatecolorcomponent),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.White
                )
            }
            Button(modifier = modo,
                onClick = {
                    navController.navigate(NavitemScreen.SingleValueFloatAnimationScreen.route)
                }) {
                Text(
                    text = stringResource(id = R.string.singlevaluefloatanimation),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.White
                )
            }
            Button(modifier = modo,
                onClick = {
                    navController.navigate(NavitemScreen.ElevationAnimationScreen.route)
                }) {
                Text(
                    text = stringResource(id = R.string.elevationanimation),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.White
                )
            }
            Button(modifier = modo,
                onClick = {
                    navController.navigate(NavitemScreen.CustomCountDownTimer.route)
                }) {
                Text(
                    text = stringResource(id = R.string.customcountdowntimer),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.White
                )
            }
            Button(modifier = modo,
                onClick = {
                    navController.navigate(NavitemScreen.RotateAnimationScreen.route)
                }) {
                Text(
                    text = stringResource(id = R.string.rotateanimation),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.White
                )
            }
            Button(modifier = modo,
                onClick = {
                    navController.navigate(NavitemScreen.AnimatingFonts.route)
                }) {
                Text(
                    text = stringResource(id = R.string.animatingfonts),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.White
                )
            }
            Button(modifier = modo,
                onClick = {
                    navController.navigate(NavitemScreen.DraggableMusicKnob.route)
                }) {
                Text(
                    text = stringResource(id = R.string.draggablemusicknob),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.White
                )
            }
            Button(
                onClick = {
                    navController.navigate(NavitemScreen.CounterText.route)
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.countertext),
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    color = Color.White
                )
            }
            Button(
                onClick = {
                    navController.navigate(NavitemScreen.AnimatableScreen.route)
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.animatablescreen),
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    color = Color.White
                )
            }
        }
    )
}

@Preview
@Composable
fun AnimationScreenPreview() {
    AnimationScreen(navController = rememberNavController())
}