package com.preciado.todo.features.todo_tasks

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.preciado.todo.core.composables.composables_todo.views.TODOListView
import com.preciado.todo.core.models.app_models.Task
import com.preciado.todo.core.navigation.Screen
import com.preciado.todo.data.CRUD_Operation
import com.preciado.todo.features.todo_tasks.components.TaskItem
import com.preciado.todo.features.todo_tasks.core.TodoTasksVM

@Composable
fun TodoTasksView(
    navController: NavController,
    listId: Int,
    vm: TodoTasksVM? = hiltViewModel()
) {
    LaunchedEffect(key1 = vm) {
        vm!!.navController = navController
        vm.onLoad(listId)
    }

    val list by vm!!.loadList(listId).collectAsStateWithLifecycle(initialValue = emptyList())

    TODOListView<Task>(
        title = vm!!.title,
        backButtonVisible = true,
        onBackButtonClicked = {
            vm.onBackButtonClicked()
        },
        onFloatingActionButtonClicked = {
            vm.navigateTo(Screen.AddEditList.withArgs(CRUD_Operation.CREATE.ordinal.toString(), "0"))
        },
        list = list ?: emptyList(),
        emptyListMessage = {

        }) { task ->
        TaskItem(
            onClick = {
                vm.navigateTo("")
            },
            task = task,
            onChecked = {

            }
        )
    }
}