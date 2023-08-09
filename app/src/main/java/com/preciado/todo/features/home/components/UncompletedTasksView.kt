package com.preciado.todo.features.home.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.preciado.todo.core.models.Task

@Composable
fun UncompletedTasksView(
    navController: NavController,
    uncompletedTasks: List<Task> = emptyList()
){
    LazyColumn(
        contentPadding = PaddingValues(5.dp)
    ){
        items(uncompletedTasks){ task ->
            TaskItemView(navController = navController, task = task)
        }
    }
}