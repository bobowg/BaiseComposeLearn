package com.example.baisecomposelearn.screens.room

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Restore
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.example.baisecomposelearn.NoteScreens.room.AppDrawer
import com.example.baisecomposelearn.data.database.model.NoteModel
import com.example.baisecomposelearn.model.noteviewmodel.NoteViewModel
import com.example.baisecomposelearn.navitegation.NoteScreen
import kotlinx.coroutines.launch

private const val NO_DIALOG = 1
private const val RESTORE_NOTES_DIALOG = 2
private const val PERMANENTLY_DELETE_DIALOG = 3

@Composable
fun TrashScreen(
    viewModel: NoteViewModel
) {
    val notesInThrash: List<NoteModel> by viewModel.notesInTrash.observeAsState(listOf())
    val selectedNotes: List<NoteModel> by viewModel.selectedNotes.observeAsState(listOf())
    val dialogState: MutableState<Int> = rememberSaveable { mutableStateOf(NO_DIALOG) }
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            val areActionsVisible = selectedNotes.isNotEmpty()
            TrashTopAppBar(
                onNavigationIconClick = { coroutineScope.launch { scaffoldState.drawerState.open() } },
                onRestoreNotesClick = { dialogState.value = RESTORE_NOTES_DIALOG },
                onDeleteNotesClick = { dialogState.value = PERMANENTLY_DELETE_DIALOG },
                areActionsVisible = areActionsVisible
            )
        },
        scaffoldState = scaffoldState,
        drawerContent = {
            AppDrawer(
                currentScreen = NoteScreen.Trash,
                closeDrawerAction = {
                    coroutineScope.launch { scaffoldState.drawerState.close() }
                }
            )
        },
        content = { PaddingValues ->
            Content(
                notes = notesInThrash,
                onNoteClick = { viewModel.onNoteSelected(it) },
                selectedNotes = selectedNotes
            )
            val dialog = dialogState.value
            if (dialog != NO_DIALOG) {
                val confirmAction: () -> Unit = when (dialog) {
                    RESTORE_NOTES_DIALOG -> {
                        {
                            viewModel.restoreNotes(selectedNotes)
                            dialogState.value = NO_DIALOG
                        }
                    }
                    PERMANENTLY_DELETE_DIALOG -> {
                        {
                            viewModel.permanentlyDeleteNotes(selectedNotes)
                            dialogState.value = NO_DIALOG
                        }
                    }
                    else -> {
                        {
                            dialogState.value = NO_DIALOG
                        }
                    }
                }

                AlertDialog(
                    modifier = Modifier.padding(PaddingValues),
                    onDismissRequest = { dialogState.value = NO_DIALOG },
                    title = { Text(mapDialogTitle(dialog)) },
                    text = { Text(mapDialogText(dialog)) },
                    confirmButton = {
                        TextButton(onClick = confirmAction) {
                            Text("断续")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { dialogState.value = NO_DIALOG }) {
                            Text("取消")
                        }
                    }
                )
            }
        }
    )
}

@Composable
private fun TrashTopAppBar(
    onNavigationIconClick: () -> Unit,
    onRestoreNotesClick: () -> Unit,
    onDeleteNotesClick: () -> Unit,
    areActionsVisible: Boolean
) {
    TopAppBar(
        title = {
            Text(text = "垃圾箱", color = MaterialTheme.colors.onPrimary)
        },
        navigationIcon = {
            IconButton(onClick = onNavigationIconClick) {
                Icon(imageVector = Icons.Default.List, contentDescription = "Drawer Button")
            }
        },
        actions = {
            if (areActionsVisible) {
                IconButton(onClick = onRestoreNotesClick) {

                    Icon(
                        imageVector = Icons.Default.Restore,
                        contentDescription = "Restore Notes Trash",
                        tint = MaterialTheme.colors.onPrimary
                    )
                }
                IconButton(onClick = onDeleteNotesClick) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete Notes Trash",
                        tint = MaterialTheme.colors.onPrimary
                    )
                }
            }
        }
    )

}

@Composable
private fun Content(
    notes: List<NoteModel>,
    onNoteClick: (NoteModel) -> Unit,
    selectedNotes: List<NoteModel>
) {
    val tabs = listOf("常规", "检查")
    var selectedTab by remember { mutableStateOf(0) }
    Column {
        LazyColumn {
            items(count = notes.size) { noteIndex ->
                val note = notes[noteIndex]
                val isNoteSelected = selectedNotes.contains(note)
                Note(note = note, onNoteClick = onNoteClick, isSelected = isNoteSelected)
            }
        }
    }
}

private fun mapDialogTitle(dialog: Int): String = when (dialog) {
    RESTORE_NOTES_DIALOG -> "回复记事"
    PERMANENTLY_DELETE_DIALOG -> "永久删除"
    else -> throw RuntimeException("Dialog not supported:$dialog")
}

private fun mapDialogText(dialog: Int): String = when (dialog) {
    RESTORE_NOTES_DIALOG -> "您确定要恢复选定的笔记吗？"
    PERMANENTLY_DELETE_DIALOG -> "您确定要永久删除选定的笔记吗？"
    else -> throw RuntimeException("Dialog not supported:$dialog")
}