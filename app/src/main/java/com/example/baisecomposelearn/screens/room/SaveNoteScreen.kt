package com.example.baisecomposelearn.screens.room

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.baisecomposelearn.data.database.model.NEW_NOTE_ID
import com.example.baisecomposelearn.data.database.model.NoteModel
import com.example.baisecomposelearn.model.noteviewmodel.NoteViewModel
import com.example.baisecomposelearn.navitegation.JetNotesRouter
import com.example.baisecomposelearn.navitegation.NoteScreen

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SaveNoteScreen(
    viewModel: NoteViewModel
) {
    val noteEntry: NoteModel by viewModel.noteEntry.observeAsState(NoteModel())
    val bottomDrawerState: BottomDrawerState = rememberBottomDrawerState(BottomDrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val moveNoteToTrashDialogShownState: MutableState<Boolean> = rememberSaveable {
        mutableStateOf(false)
    }
    Scaffold(
        topBar = {
            val isEditingMode: Boolean = noteEntry.id != NEW_NOTE_ID
            SaveNoteTopAppBar(
                isEditingMode = isEditingMode,
                onBackClick = { JetNotesRouter.navigateTo(NoteScreen.RoomDatabaseScreen) },
                onSaveNoteClick = {
                    viewModel.saveNote(noteEntry)
                },
                onDeleteNoteClick = {
                    moveNoteToTrashDialogShownState.value = true
                }
            )
        },
        content = { PaddingValues ->
            if (moveNoteToTrashDialogShownState.value) {
                AlertDialog(
                    modifier = Modifier.padding(PaddingValues),
                    onDismissRequest = { moveNoteToTrashDialogShownState.value = false },
                    title = { Text(text = "把纸条移到垃圾桶") },
                    text = {
                        Text(text = "你确定要把这张纸条移到垃圾箱？")
                    },
                    confirmButton = {
                        TextButton(onClick = { viewModel.moveNoteToTrash(noteEntry) }) {
                            Text(text = "继续吗？")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = {
                            moveNoteToTrashDialogShownState.value = false
                        }) {
                            Text(text = "取消")
                        }
                    }
                )
            }
            SaveNoteContent(note = noteEntry, onNoteChange = { updateNoteEntry ->
                viewModel.onNoteEntryChange(updateNoteEntry)
            })

        }
    )
}

@Composable
private fun SaveNoteTopAppBar(
    isEditingMode: Boolean,
    onBackClick: () -> Unit,
    onSaveNoteClick: () -> Unit,
    onDeleteNoteClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = "保存记事", color = MaterialTheme.colors.onPrimary)
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Save Note ArrowBack",
                    tint = MaterialTheme.colors.onPrimary
                )
            }
        },
        actions = {
            IconButton(onClick = onSaveNoteClick) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Save Note",
                    tint = MaterialTheme.colors.onPrimary
                )
            }
            if (isEditingMode) {
                IconButton(onClick = onDeleteNoteClick) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete Note Button",
                        tint = MaterialTheme.colors.onPrimary
                    )
                }
            }
        }

    )
}

@Preview
@Composable
private fun SaveNoteTopAppBarPrivew() {
    SaveNoteTopAppBar(
        isEditingMode = true,
        onBackClick = { /*TODO*/ },
        onSaveNoteClick = { /*TODO*/ },
        onDeleteNoteClick = {}
    )
}


@Composable
private fun NoteCheckOption(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .padding(top = 16.dp)
    ) {
        Text(text = "勾选删除笔记？", modifier = Modifier.weight(1f))
        Switch(
            checked = isChecked,
            onCheckedChange = onCheckedChange,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Preview
@Composable
fun NoteCheckOptionPrivew() {
    NoteCheckOption(isChecked = true, onCheckedChange = {})
}

@Composable
private fun ContentTextField(
    label: String,
    text: String,
    onTextChange: (String) -> Unit
) {
    TextField(
        value = text,
        onValueChange = onTextChange,
        label = { Text(text = label) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface
        )
    )
}

@Preview
@Composable
private fun ContentTextFieldPrivew() {
    ContentTextField(label = "Title", text = "", onTextChange = {})
}

@Composable
private fun SaveNoteContent(
    note: NoteModel,
    onNoteChange: (NoteModel) -> Unit
) {

    Column(modifier = Modifier.fillMaxSize()) {
        ContentTextField(label = "标题", text = note.title, onTextChange = { newTitle ->
            onNoteChange.invoke(note.copy(title = newTitle))
        })
        ContentTextField(label = "正文", text = note.content, onTextChange = { newContent ->
            onNoteChange.invoke(note.copy(content = newContent))
        })
    }
}

@Preview
@Composable
fun SaveNoteContentPrivew() {
    SaveNoteContent(note = NoteModel(1, "title", "content"), onNoteChange = {})
}