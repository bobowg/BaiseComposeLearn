package com.example.baisecomposelearn.screens.room

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.baisecomposelearn.NoteScreens.room.AppDrawer
import com.example.baisecomposelearn.data.database.model.NoteModel
import com.example.baisecomposelearn.model.noteviewmodel.NoteViewModel
import com.example.baisecomposelearn.navitegation.NoteScreen
import kotlinx.coroutines.launch


@Composable
fun RoomDatabaseScreen(viewModel: NoteViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val notes: List<NoteModel> by viewModel.notesNotInTrash.observeAsState(listOf())
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "记事本",
                        color = MaterialTheme.colors.onPrimary
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        coroutineScope.launch { scaffoldState.drawerState.open() }
                    }) {
                        Icon(
                            imageVector = Icons.Filled.List,
                            contentDescription = "Drawer Button"
                        )
                    }
                }
            )
        },
        scaffoldState = scaffoldState,
        drawerContent = {
            AppDrawer(
                currentScreen = NoteScreen.RoomDatabaseScreen,
                closeDrawerAction = {
                    coroutineScope.launch {
                        scaffoldState.drawerState.close()
                    }
                }
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onCreateNewNoteClick()
                },
                contentColor = MaterialTheme.colors.background,
                content = {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add Note Button")
                })
        },
        content = { PaddingValues ->
            if (notes.isNotEmpty()) {
                NotesList(
                    modifier = Modifier.padding(PaddingValues),
                    notes = notes,
                    onNoteCheckedChange = { viewModel.onNoteCheckedChange(it) },
                    onNoteClick = { viewModel.onNoteClick(it) }
                )
            }
        }
    )
}

@Composable
private fun NotesList(
    notes: List<NoteModel>,
    onNoteCheckedChange: (NoteModel) -> Unit,
    onNoteClick: (NoteModel) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn {
        items(count = notes.size) { noteIndex ->
            val note = notes[noteIndex]
            Note(
                note = note,
                onNoteClick = onNoteClick,
                onNoteCheckedChange = onNoteCheckedChange,
                isSelected = false
            )
        }
    }
}


@Preview
@Composable
fun NotesListPrivew() {
    NotesList(notes = listOf(
        NoteModel(1, "Note 1", "Content 1"),
        NoteModel(2, "Note 2", "Content 2"),
        NoteModel(3, "Note 3", "Content 3"),
        NoteModel(4, "Note 4", "Content 4"),
        NoteModel(5, "Note 5", "Content 5"),
    ), onNoteCheckedChange = {}, onNoteClick = {})
}