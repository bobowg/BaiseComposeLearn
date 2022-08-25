package com.example.baisecomposelearn.screens.viewmodel

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.baisecomposelearn.R
import com.example.baisecomposelearn.model.WellnessTask

@Composable
fun WellnessTasksList(
    list: List<WellnessTask>,
    onCheckedTask: (WellnessTask, Boolean) -> Unit,
    onCloseTask: (WellnessTask) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(
            items = list,
            /**
             * 使用 key param 定义表示可变列表中项目的唯一键，
             * 而不是使用默认键（列表位置）。 这可以防止不必要的
             * 重组。
             */
            key = { task -> task.id }
        ) { task ->
            WellnessTaskItem(
                taskName = task.lable,
                checked = task.checked,
                onCkeckChange = {checked-> onCheckedTask(task,checked) },
                onClose = {onCloseTask(task)}
            )
        }
    }
}

@Composable
fun WellnessTaskItem(
    taskName: String,
    checked: Boolean,
    onCkeckChange: (Boolean) -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Text(text = taskName, modifier = Modifier
            .weight(1f)
            .padding(start = 16.dp))
        Checkbox(checked = checked, onCheckedChange = onCkeckChange)
        IconButton(onClick = onClose) {
            Icon(imageVector = Icons.Filled.Close, contentDescription = stringResource(id = R.string.close))
        }
    }
}
