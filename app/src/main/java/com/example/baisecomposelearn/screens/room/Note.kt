package com.example.baisecomposelearn.screens.room

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.baisecomposelearn.data.database.model.NoteModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Note(
    modifier: Modifier = Modifier,
    note: NoteModel,
    onNoteClick: (NoteModel) -> Unit = {},
    onNoteCheckedChange: (NoteModel) -> Unit = {},
    isSelected: Boolean = false
) {
    val background = if (isSelected) Color.LightGray else MaterialTheme.colors.surface
    Card(
        shape = RoundedCornerShape(4.dp),
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(),
        backgroundColor = background
    ) {
        ListItem(
            text = { Text(text = note.title, maxLines = 1) },
            secondaryText = {
                Text(text = note.content, maxLines = 10)
            },
            modifier = Modifier.clickable {
                onNoteClick.invoke(note)
            }
        )
    }

}

@Preview
@Composable
private fun NotePrivew() {
    Note(note = NoteModel(1, "Note1", "Content 1"), isSelected = false)
}