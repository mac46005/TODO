package com.preciado.todo.features.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.preciado.todo.core.models.Task

@Composable
fun TasksView(
    navController: NavController,
    uncompletedTasks: List<Task> = emptyList(),
    completedTasks: List<Task> = emptyList()
){
    Column(

    ) {
        UncompletedTasksView(navController = navController)
    }
}