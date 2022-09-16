package com.example.baisecomposelearn.screens.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProviderInfo
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.widget.AppWidgetPinnedReceiver


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppWidgetScreen() {
    AppInfoText()
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppInfoText() {
    val context = LocalContext.current
    val packageName = context.packageName
    val widgetManager = AppWidgetManager.getInstance(context)
    val widgetProviders = widgetManager.getInstalledProvidersForPackage(packageName, null)
    Scaffold {
        LazyColumn(contentPadding = it ) {
            item {
                Text(
                    text = stringResource(id = R.string.placeholder_main_activate),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
            if (!widgetManager.isRequestPinAppWidgetSupported) {
                item {
                    Text(
                        text = stringResource(id = R.string.placeholder_main_activity_pin_unavailable),
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colors.error)
                            .padding(16.dp),
                        color = MaterialTheme.colors.onError
                    )
                }
            }
            items(widgetProviders){ providerInfo->
                WidgetInfoCard(providerInfo)
            }
        }
    }


}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WidgetInfoCard(providerInfo: AppWidgetProviderInfo) {
    val context = LocalContext.current
    val label = providerInfo.loadLabel(context.packageManager)
    val description = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        providerInfo.loadDescription(context).toString()
    } else {
        "Description not available"
    }
    val preview = painterResource(id = providerInfo.previewImage)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        onClick = {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                providerInfo.pin(context)
            }
        }
    ) {
        Row(modifier = Modifier.padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Column {
                Text(
                    text = label,
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.body1
                )
            }
            Image(painter = preview, contentDescription = description)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
private fun AppWidgetProviderInfo.pin(context: Context) {
    val successCallback = PendingIntent.getBroadcast(
        context,
        0,
        Intent(context, AppWidgetPinnedReceiver::class.java),
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    AppWidgetManager.getInstance(context).requestPinAppWidget(provider, null, successCallback)
}
