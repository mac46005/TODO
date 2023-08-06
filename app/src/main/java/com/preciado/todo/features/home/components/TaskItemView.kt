package com.preciado.todo.features.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.preciado.todo.core.models.TODOListTask

@Composable
fun TaskItemView(
    todoListTask: TODOListTask,
    navController: NavController
){
    Box(
        modifier = Modifier.clickable {
            navController.navigate("task_details/${todoListTask.todoList_id}/${todoListTask.todoList_id}")
        }
    ){

    }
}