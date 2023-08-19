package com.preciado.todo.features.home.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import com.preciado.todo.core.models.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun TaskList(
    taskList: List<Task>
){
    val taskList by remember{ mutableStateOf(taskList) }

    LazyColumn(
        contentPadding = PaddingValues(5.dp)
    ){
        items(taskList){ task ->
            Text(text = task.taskName)
        }
    }
}