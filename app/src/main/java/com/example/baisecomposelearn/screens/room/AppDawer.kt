package com.example.baisecomposelearn.NoteScreens.room

import android.content.Intent
import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BlurOff
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.baisecomposelearn.MainActivity
import com.example.baisecomposelearn.ResultActivity
import com.example.baisecomposelearn.navitegation.JetNotesRouter
import com.example.baisecomposelearn.navitegation.NoteScreen

@Composable
fun AppDrawer(
    currentScreen: NoteScreen,
    closeDrawerAction: () -> Unit
) {
    val context = LocalContext.current
    Column(modifier = Modifier.fillMaxSize()) {
        AppDrawerHeader()
        Divider(color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f))
        NoteScreenNavigationButton(
            icon = Icons.Default.Home,
            label = "记事本",
            isSelected = currentScreen == NoteScreen.RoomDatabaseScreen,
            onClick = {
                JetNotesRouter.navigateTo(NoteScreen.RoomDatabaseScreen)
                closeDrawerAction()
            }
        )
        NoteScreenNavigationButton(
            icon = Icons.Default.Delete,
            label = "垃圾",
            isSelected = currentScreen == NoteScreen.Trash,
            onClick = {
                JetNotesRouter.navigateTo(NoteScreen.Trash)
                closeDrawerAction()
            })
        NoteScreenNavigationButton(
            icon = Icons.Default.BlurOff,
            label = "返回",
            false,
            onClick = {
                val sendIntent = Intent(context, MainActivity::class.java)
                ContextCompat.startActivity(context, sendIntent, Bundle.EMPTY)
            })

    }
}

@Preview
@Composable
private fun AppDrawerPrivew() {
    AppDrawer(NoteScreen.RoomDatabaseScreen, {})
}

@Composable
private fun NoteScreenNavigationButton(
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val color = MaterialTheme.colors
    val imageAlpha = if (isSelected) 1f else 0.6f
    val textColor = if (isSelected) color.primary else color.onSurface.copy(alpha = 0.6f)
    val backgroundColr = if (isSelected) color.primary.copy(alpha = 0.12f) else color.surface

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        color = backgroundColr,
        shape = MaterialTheme.shapes.small
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    onClick = onClick
                )
                .padding(4.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                imageVector = icon,
                contentDescription = "NoteScreen Navigation Button",
                colorFilter = ColorFilter.tint(textColor),
                alpha = imageAlpha
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.body2,
                color = textColor,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}



@Composable
private fun AppDrawerHeader() {
    Row(modifier = Modifier.fillMaxWidth()) {
        Image(
            imageVector = Icons.Default.Menu,
            contentDescription = "Drawer Header Ico",
            colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface),
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = "记事本",
            modifier = Modifier.align(alignment = Alignment.CenterVertically)
        )
    }
}
