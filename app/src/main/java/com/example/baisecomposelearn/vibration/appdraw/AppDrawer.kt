package com.example.baisecomposelearn.vibration.appdraw

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.navitegation.HapticSamplerDestinations
import com.example.baisecomposelearn.theme.DrawerButtonShape
import com.example.baisecomposelearn.theme.HapticSamplerTheme


@Composable
fun AppDrawer(
    currentRoute: String,
    navigateToHome: () -> Unit,
    navigateToResist: () -> Unit,
    navigateToExpand: () -> Unit,
    navigateToBounce: () -> Unit,
    navigateToWobble: () -> Unit,
    closeDrawer: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        Text(
            stringResource(R.string.vibration),
            modifier = modifier
                .padding(start = 32.dp)
                .padding(vertical = 16.dp),
            style = MaterialTheme.typography.h5
        )
        DrawerButton(
            icon = Icons.Rounded.Home,
            label = stringResource(R.string.vibration),
            isSelected = currentRoute === HapticSamplerDestinations.HOME_ROUTE,
            onClick = {
                navigateToHome()
                closeDrawer()
            }
        )
        Text(
            stringResource(R.string.drawer_content_example_effects),
            style = MaterialTheme.typography.subtitle2,
            modifier = modifier
                .padding(start = 32.dp)
                .padding(vertical = 16.dp)
        )
        DrawerButton(
            icon = Icons.Rounded.Refresh,
            label = stringResource(R.string.resist_screen_title),
            isSelected = currentRoute === HapticSamplerDestinations.RESIST_ROUTE,
            onClick = {
                navigateToResist()
                closeDrawer()
            }
        )
        DrawerButton(
            icon = Icons.Rounded.OpenWith,
            label = stringResource(R.string.expand_screen_title),
            isSelected = currentRoute === HapticSamplerDestinations.EXPAND_ROUTE,
            onClick = {
                navigateToExpand()
                closeDrawer()
            }
        )
        DrawerButton(
            icon = Icons.Rounded.SportsVolleyball,
            label = stringResource(R.string.bounce),
            isSelected = currentRoute === HapticSamplerDestinations.BOUNCE_ROUTE,
            onClick = {
                navigateToBounce()
                closeDrawer()
            }
        )
        DrawerButton(
            icon = Icons.Rounded.Water,
            label = stringResource(R.string.wobble),
            currentRoute === HapticSamplerDestinations.WOBBLE_ROUTE,
            onClick = {
                navigateToWobble()
                closeDrawer()
            }
        )
    }
}

@Composable
private fun DrawerButton(
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier

) {
    val backgroundColor = if (isSelected) {
        MaterialTheme.colors.secondary
    } else {
        Color.Transparent
    }

    Surface(
        color = backgroundColor,
        shape = DrawerButtonShape,
        modifier = modifier
            .padding(horizontal = 8.dp)
            .fillMaxWidth(),
    ) {
        TextButton(
            onClick = onClick,
            modifier = modifier
                .padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
                .fillMaxWidth()

        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                val textAndIconColor = if (isSelected) MaterialTheme.colors.onSecondary
                else MaterialTheme.colors.secondary
                Icon(icon, contentDescription = null, tint = textAndIconColor)
                Spacer(Modifier.width(16.dp))
                Text(
                    text = label,
                    color = textAndIconColor,
                    style = MaterialTheme.typography.subtitle2,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppDrawerPreview() {
    HapticSamplerTheme {
        AppDrawer(
            HapticSamplerDestinations.HOME_ROUTE,
            navigateToHome = {},
            navigateToResist = {},
            navigateToExpand = {},
            navigateToBounce = {},
            navigateToWobble = {},
            closeDrawer = {},
        )
    }
}