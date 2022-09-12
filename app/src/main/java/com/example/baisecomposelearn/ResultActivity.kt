package com.example.baisecomposelearn

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.example.baisecomposelearn.model.noteviewmodel.NoteViewModel
import com.example.baisecomposelearn.model.noteviewmodel.NoteViewModelFactory
import com.example.baisecomposelearn.navitegation.JetNotesRouter
import com.example.baisecomposelearn.navitegation.NoteScreen
import com.example.baisecomposelearn.screens.room.RoomDatabaseScreen
import com.example.baisecomposelearn.screens.room.SaveNoteScreen
import com.example.baisecomposelearn.screens.room.TrashScreen
import com.example.baisecomposelearn.theme.BaiseComposeLearnTheme

class ResultActivity : AppCompatActivity() {
    val viewModel: NoteViewModel by viewModels(factoryProducer = {
        NoteViewModelFactory(
            this,
            (application as AppStart).dependencyInjector.repository
        )
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaiseComposeLearnTheme {
               MainActivityScreen(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun MainActivityScreen(viewModel: NoteViewModel) {
    Surface {
        when (JetNotesRouter.currentScreen) {
            is NoteScreen.RoomDatabaseScreen -> RoomDatabaseScreen(viewModel = viewModel)
            is NoteScreen.SaveNote -> SaveNoteScreen(viewModel = viewModel)
            is NoteScreen.Trash -> TrashScreen(viewModel = viewModel)
        }
    }
}