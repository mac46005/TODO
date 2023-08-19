package com.preciado.todo.features.home.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import com.preciado.todo.core.models.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun TaskList(
    taskListFlow: Flow<List<Task>> = emptyFlow()
){
    val taskList by taskListFlow.collectAsState(initial = emptyList())

    LazyColumn(
        contentPadding = PaddingValues(5.dp)
    ){
        items(taskList){ task ->
            Text(text = task.taskName)
        }
    }
}