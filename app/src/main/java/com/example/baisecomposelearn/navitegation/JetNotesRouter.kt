package com.example.baisecomposelearn.navitegation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

sealed class NoteScreen {
    object RoomDatabaseScreen : NoteScreen()
    object SaveNote : NoteScreen()
    object Trash : NoteScreen()
}

object JetNotesRouter {
    var currentScreen: NoteScreen by mutableStateOf(NoteScreen.RoomDatabaseScreen)

    fun navigateTo(destination: NoteScreen) {
        currentScreen = destination
    }
}