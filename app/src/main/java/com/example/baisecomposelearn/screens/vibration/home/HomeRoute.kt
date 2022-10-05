package com.example.baisecomposelearn.screens.vibration.home

import android.os.Build
import android.os.VibrationEffect
import android.view.View
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.theme.BaiseComposeLearnTheme
import com.example.baisecomposelearn.theme.HapticSamplerTheme
import com.example.baisecomposelearn.theme.buttonSurface
import com.example.baisecomposelearn.theme.buttonSurfaceDisabled
import com.example.baisecomposelearn.vibration.viewmodel.*
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun HomeRoute(viewModel: VibraionViewModel) {
    HomeScreen(
        homeUiState = viewModel.homeUiState, onButtonClicked = viewModel::onButtonClicked,
        onSnackbarMessage = viewModel::onSnackbarMessage, scrollState = viewModel.scrollState,
    )
}

@Composable
private fun HomeScreen(
    homeUiState: HomeUiState,
    onButtonClicked: (view: View, hapticCategory: HapticCategoryType, hapticId: Int) -> Unit,
    onSnackbarMessage: (message: String) -> Unit,
    scrollState: ScrollState = rememberScrollState(),
) {
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(id = R.string.vibration),
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(top = 36.dp, bottom = 16.dp)
        )
        for (category in homeUiState.hapticCategories) {
            HomeHapticCategory(lable = category.label) {
                for (buttons in category.buttons.chunked(2)) {
                    Row(
                        modifier = Modifier
                            .padding(bottom = 16.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        for ((index, button) in buttons.withIndex()) {
                            // Add some spacing before the second button.
                            if (index == 1) {
                                Spacer(modifier = Modifier.width(8.dp))
                            }
                            HomeHapticButton(
                                label = button.label,
                                isEnabled = button.worksOnUserDevice,
                                onClick = {
                                    if (!button.worksOnUserDevice) {
                                        coroutineScope.launch {
                                            onSnackbarMessage("${button.label} 不支持些设备.")
                                        }
                                        return@HomeHapticButton
                                    }
                                    onButtonClicked(it, category.categoryType, button.hapticId)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun HomeHapticButton(
    label: String,
    isEnabled: Boolean = true,
    onClick: (view: View) -> Unit,
    modifier: Modifier = Modifier
) {
    val buttonColor =
        if (isEnabled) MaterialTheme.colors.buttonSurfaceDisabled else MaterialTheme.colors.buttonSurface
    val textColor = if (isEnabled) MaterialTheme.colors.onPrimary else MaterialTheme.colors.primary
    val view = LocalView.current
    TextButton(
        onClick = { onClick(view) },
        modifier = modifier
            .height(64.dp)
            .width(180.dp)
            .padding(4.dp)
            .background(color = buttonColor, shape = MaterialTheme.shapes.large)
    ) {
        Text(text = label, color = textColor, style = MaterialTheme.typography.subtitle2)
    }
}

@Composable
private fun HomeHapticCategory(lable: String, content: @Composable () -> Unit) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 16.dp)
        ) {
            Text(
                text = lable,
                style = MaterialTheme.typography.subtitle2,
                color = MaterialTheme.colors.secondary
            )
        }
        content()
    }
}
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HapticSamplerTheme {
        HomeScreen(
            homeUiState = HomeUiState(
                listOf(
                    HapticCategory(
                        "Effects",
                        categoryType = HapticCategoryType.PREDEFINED_EFFECTS,
                        buttons = listOf(
                            HapticButton(
                                "Tick", true, VibrationEffect.EFFECT_TICK
                            ),
                            HapticButton(
                                "Click", false, VibrationEffect.EFFECT_CLICK
                            )
                        )
                    ),
                    HapticCategory(
                        "Primitives",
                        categoryType = HapticCategoryType.COMPOSITION_PRIMITIVES,
                        buttons = listOf(
                            HapticButton(
                                "Spin",
                                true,
                                VibrationEffect.Composition.PRIMITIVE_SPIN
                            ),
                            HapticButton(
                                "Thud",
                                false,
                                VibrationEffect.Composition.PRIMITIVE_THUD
                            )
                        )
                    )
                )
            ),
            onButtonClicked = { view, hapticCategoryType, hapticId -> },
            onSnackbarMessage = {}
        )
    }
}