package com.example.baisecomposelearn.screens.vibration.bounce

import android.annotation.SuppressLint
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.TouchApp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.screens.components.Screen
import com.example.baisecomposelearn.theme.HapticSamplerTheme
import com.example.baisecomposelearn.utils.noRippleClickable
import com.example.baisecomposelearn.vibration.viewmodel.BounceViewModel
import kotlin.math.pow

/**
 * The two positions of the animated ball. Start refers to the elevated state in which
 * the ball drops from, and End once the ball is at rest on the "floor".
 */
private enum class BallPosition {
    Start,
    End,
}

private val BALL_SIZE = 64.dp
private val BALL_DROP_HEIGHT_DP = 300.dp
private val BALL_START_POSITION = -(BALL_DROP_HEIGHT_DP)
private val BALL_END_POSITION = 0.dp

// If recomposition occurs within these ranges, we consider it a collision and will vibrate.
// Speed of animation impacts the ranges we select. For example, we must have a larger range to
// detect initial collision with floor as ball animates quickly through it, otherwise
// it'd be possible for no recomposition to occur within range.
private val DISTANCE_FROM_END_POSITION_FOR_COLLISION = 5.dp
private val DISTANCE_FROM_START_POSITION_FOR_COLLISION = 1.dp

private val FLOOR_SIZE = 80.dp

private const val DROP_ANIMATION_TIME_MS = 3000
private const val RESET_ANIMATION_TIME_MS = 1000

@Composable
fun BounceRoute(viewModel: BounceViewModel) {
    BounceExampleScreen(messageToUser = viewModel.messageToUser)
}

@Composable
fun BounceExampleScreen(messageToUser: String) {
    var ballPosition by remember { mutableStateOf(BallPosition.Start) }
    var bounceCount by remember { mutableStateOf(0) }

    val vibrator = LocalContext.current.getSystemService(Vibrator::class.java) as Vibrator

    val transitionData = updateTransitionData(ballPosition)
    val collisionData = updateCollisionData(transitionData)

    // Execute a click vibration once the ball has been reset.
    var hasVibratedForReset by remember { mutableStateOf(false) }
    if (collisionData.collisionWithReset) {
        if (!hasVibratedForReset) {
            clickVibration(vibrator)
            hasVibratedForReset = true
        }
    } else {
        hasVibratedForReset = false
    }

    // Ball is about to contact floor, only vibrating once per collision.
    var hasVibratedForBallContact by remember { mutableStateOf(false) }
    if (collisionData.collisionWithFloor) {
        if (!hasVibratedForBallContact) {
            thudVibration(vibrator, 0.7.pow(bounceCount++).toFloat())
            hasVibratedForBallContact = true
        }
    } else {
        // Reset for next contact with floor.
        hasVibratedForBallContact = false
    }

    Screen(pageTitle = stringResource(R.string.bounce), messageToUser = messageToUser) {
        Box(
            Modifier
                .fillMaxSize()
                .noRippleClickable {
                    if (transitionData.isAtStart) {
                        ballPosition = BallPosition.End
                    } else {
                        // Reset the position, with a thud vibration to simulate bounce off the floor.
                        ballPosition = BallPosition.Start
                        bounceCount = 0
                        thudVibration(vibrator)
                    }
                },
        ) {

            var instructionsText: String = "" // Don't display any instructions when bouncing.
            if (transitionData.isAtStart) {
                instructionsText = stringResource(R.string.bounce_tap_to_drop)
            } else if (transitionData.isAtEnd || transitionData.isResetting) {
                // Display instructions to reset only when the ball is on floor or resetting.
                instructionsText = stringResource(R.string.bounce_tap_to_reset)
            }
            Text(
                text = instructionsText,
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.BottomCenter)
                    .offset(y = -(BALL_DROP_HEIGHT_DP + BALL_SIZE + FLOOR_SIZE))
            )

            Column(
                modifier = Modifier.align(Alignment.BottomStart),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Ball(
                    offsetY = transitionData.ballOffsetY,
                    displayTouchIndicator = transitionData.isAtStart
                )
                Floor()
            }
        }
    }
}

@Composable
private fun Ball(offsetY: Dp = 0.dp, displayTouchIndicator: Boolean = false) {
    Box(
        modifier = Modifier
            .offset(y = offsetY)
            .size(BALL_SIZE)
            .clip(CircleShape)
            .background(MaterialTheme.colors.primaryVariant)
    ) {
        if (displayTouchIndicator) {
            Icon(
                Icons.Outlined.TouchApp, contentDescription = null,
                Modifier.align(
                    Alignment.Center
                ),
                MaterialTheme.colors.onPrimary
            )
        }
    }
}

/**
 * Floor the ball impacts against.
 */
@Composable
private fun Floor() {
    Box(
        Modifier
            .height(FLOOR_SIZE)
            .fillMaxWidth()
            .background(MaterialTheme.colors.primaryVariant)
    )
}

/**
 * Hold the transition values for the ball being dropped and reset.
 */
private data class TransitionData(
    val ballOffsetY: Dp,
    val isAtStart: Boolean, // Transition is complete and ball is in start position.
    val isAtEnd: Boolean, // Transition is complete and now at rest on floor.
    val isResetting: Boolean, // Currently being reset (ball raising back up) to starting position.
    val isBouncing: Boolean, // Ball has been released and is bouncing.
)

/**
 * Create a transition and return animation values for animating the ball drop.
 */
@Composable
private fun updateTransitionData(ballPosition: BallPosition): TransitionData {
    val transition =
        updateTransition(ballPosition, label = "Transition between ball raised and dropped.")
    val ballOffsetY by transition.animateDp(
        transitionSpec = {
            when {
                BallPosition.Start isTransitioningTo BallPosition.End ->
                    tween(DROP_ANIMATION_TIME_MS, easing = BounceEasing())
                else ->
                    tween(RESET_ANIMATION_TIME_MS, easing = LinearOutSlowInEasing)
            }
        }, label = "Ball drop y offset."
    ) { position ->
        when (position) {
            BallPosition.Start -> BALL_START_POSITION
            BallPosition.End -> BALL_END_POSITION
        }
    }
    val isAtTargetState = transition.currentState === transition.targetState
    return TransitionData(
        ballOffsetY = ballOffsetY,
        isAtStart = isAtTargetState && transition.currentState == BallPosition.Start,
        isAtEnd = isAtTargetState && transition.currentState == BallPosition.End,
        isBouncing = !isAtTargetState && transition.targetState == BallPosition.End,
        isResetting = !isAtTargetState && transition.targetState == BallPosition.Start,
    )
}

/**
 * Holder for when the ball is currently colliding with floor or reset.
 */
private class BallCollisionData(val collisionWithFloor: Boolean, val collisionWithReset: Boolean)

/**
 * Uses the current transition data to return booleans of whether the ball is currently colliding.
 *
 * When the ball is navigating through a certain range we indicate a collision. A range is necessary
 * because we do not know specifically which frames will be drawn during animation.
 */
private fun updateCollisionData(transitionData: TransitionData): BallCollisionData {
    val ballOffsetY = transitionData.ballOffsetY
    return BallCollisionData(
        collisionWithFloor = transitionData.isBouncing &&
                ballOffsetY >
                -(DISTANCE_FROM_END_POSITION_FOR_COLLISION) && ballOffsetY < BALL_END_POSITION,
        collisionWithReset = transitionData.isResetting &&
                ballOffsetY > BALL_START_POSITION &&
                ballOffsetY < (BALL_START_POSITION + DISTANCE_FROM_START_POSITION_FOR_COLLISION)

    )
}

@SuppressLint("MissingPermission")
private fun thudVibration(vibrator: Vibrator, intensity: Float = 1f) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) return
    vibrator.vibrate(
        VibrationEffect.startComposition()
            .addPrimitive(
                VibrationEffect.Composition.PRIMITIVE_THUD,
                intensity
            )
            .compose()
    )
}

@SuppressLint("MissingPermission")
private fun clickVibration(vibrator: Vibrator) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) return
    vibrator.vibrate(
        VibrationEffect.startComposition()
            .addPrimitive(
                VibrationEffect.Composition.PRIMITIVE_CLICK,
            )
            .compose()
    )
}

/**
 *  An easing function that simulates bouncing.
 *
 *  This easing function was adapted from https://android.googlesource.com/platform/frameworks/base/+/master/core/java/android/view/animation/BounceInterpolator.java
 *  for more bounces in the same amount of time.
 */
private class BounceEasing : Easing {
    override fun transform(fraction: Float): Float {
        var t = fraction
        t *= 1.5986f
        return when {
            t < 0.3535f -> bounce(t)
            t < 0.8007f -> bounce(t - 0.5771f) + 0.6f
            t < 1.1169f -> bounce(t - 0.9588f) + 0.8f
            t < 1.3405f -> bounce(t - 1.2287f) + 0.9f
            t < 1.4986f -> bounce(t - 1.419557f) + 0.95f
            else -> bounce(t - 1.5486f) + 0.98f
        }
    }

    private fun bounce(t: Float): Float {
        return t * t * 8.0f
    }
}