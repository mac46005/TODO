package com.preciado.todo.features.home.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.preciado.todo.core.models.Task

@Composable
fun UncompletedTasksView(
    modifier: Modifier = Modifier,
    navController: NavController,
    uncompletedTasks: List<Task> = emptyList()
){
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(5.dp)
    ){
        items(uncompletedTasks){ task ->
            TaskItem(navController = navController, task = task)
        }
    }
}