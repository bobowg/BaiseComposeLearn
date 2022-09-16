package com.example.baisecomposelearn.widget

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.widget.RemoteViews
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.glance.*
import androidx.glance.action.ActionParameters
import androidx.glance.action.actionParametersOf
import androidx.glance.action.actionStartActivity
import androidx.glance.appwidget.*
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.action.ToggleableStateKey
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.padding
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.example.baisecomposelearn.MainActivity
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.theme.BaiseComposeLearnTheme
import com.example.baisecomposelearn.utils.appWidgetBackgroundCornerRadius


private val CheckboxKey = booleanPreferencesKey("checkbox")
private val SwitchKey = booleanPreferencesKey("switch")
private val SelectedKey = ActionParameters.Key<String>("key")

class ButtonsGlanceWidget : GlanceAppWidget() {
    @SuppressLint("RemoteViewLayout")
    @Composable
    override fun Content() {
        MaterialTheme {
            Column(
                modifier = GlanceModifier.fillMaxSize().padding(16.dp).appWidgetBackground()
                    .background(color = Color.Green).appWidgetBackgroundCornerRadius()
            ) {
                Text(
                    text = LocalContext.current.getString(R.string.button_title),
                    modifier = GlanceModifier.fillMaxSize().padding(8.dp),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = ColorProvider(R.color.colorPrimary)
                    ),
                )
                LazyColumn {
                    item {
                        Button(
                            text = "Button",
                            onClick = actionStartActivity<MainActivity>(),
                            modifier = GlanceModifier.fillMaxWidth()
                        )
                    }
                    item {
                        CheckBox(
                            text = "CheckBox",
                            checked = currentState(key = CheckboxKey) ?: false,
                            onCheckedChange = actionRunCallback<CompoundButtonAction>(
                                actionParametersOf(SelectedKey to CheckboxKey.name)
                            )
                        )
                    }
                    item {
                        Switch(
                            text = "Switch",
                            checked = currentState(key = SwitchKey) ?: false,
                            onCheckedChange = actionRunCallback<CompoundButtonAction>(
                                actionParametersOf(SelectedKey to SwitchKey.name)
                            )
                        )
                    }
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                        item {
                            // Radio buttons are not implemented yet in Glance, using the interop
                            // composable to use the RemoteView + XML
                            AndroidRemoteViews(
                                remoteViews = RemoteViews(
                                    LocalContext.current.packageName,
                                    R.layout.item_radio_buttons
                                ).apply {
                                    // This code will check the item_radio_button2 in the
                                    // item_radio_group RadioGroup
                                    setRadioGroupChecked(
                                        R.id.item_radio_group,
                                        R.id.item_radio_button2
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    class CompoundButtonAction : ActionCallback {
        override suspend fun onAction(
            context: Context,
            glanceId: GlanceId,
            parameters: ActionParameters
        ) {
            val toggled = parameters[ToggleableStateKey] ?: false
            updateAppWidgetState(context, glanceId) { prefs ->
                val key =
                    booleanPreferencesKey(parameters[SelectedKey] ?: return@updateAppWidgetState)
                prefs[key] = toggled
            }
            ButtonsGlanceWidget().update(context, glanceId)
        }

    }
}

class ButtonsGlanceWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget
        get() = ButtonsGlanceWidget()

}