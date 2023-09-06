package com.preciado.todo.features.todo_tasks

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.preciado.todo.core.composables.composables_todo.views.TODOListView
import com.preciado.todo.core.models.app_models.Task
import com.preciado.todo.features.todo_tasks.core.TodoTasksVM

@Composable
fun TodoTasks(
    navController: NavController,
    listId: Int,
    vm: TodoTasksVM? = hiltViewModel()
){
    LaunchedEffect(key1 = vm){
        vm!!.navController = navController
    }

    val list by vm!!.loadList(listId).collectAsStateWithLifecycle(initialValue = emptyList())

    TODOListView<Task>(
        onFloatingActionButtonClicked = {
            vm!!.navigateTo()
        },
        list = list?: emptyList(),
        emptyListMessage = {

        }) {task ->

    }
}