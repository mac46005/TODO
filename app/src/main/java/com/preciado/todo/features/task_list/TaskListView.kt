package com.preciado.todo.features.task_list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.preciado.todo.core.composables.composables_todo.views.TODOListView
import com.preciado.todo.core.models.app_models.models.Task
import com.preciado.todo.core.navigation.Screen
import com.preciado.todo.data.CRUD_Operation
import com.preciado.todo.features.task_list.components.TaskItem
import com.preciado.todo.features.task_list.core.TaskListVM

@Composable
fun TaskListView(
    navController: NavController,
    listId: Int,
    vm: TaskListVM? = hiltViewModel()
) {


    vm!!.onLoad(navController, listId)

    val list by vm.loadList(listId).collectAsStateWithLifecycle(initialValue = emptyList())

    TODOListView<Task>(
        title = vm.title,
        backButtonVisible = true,
        onBackButtonClicked = {
            vm.onBackButtonClicked()
        },
        onFloatingActionButtonClicked = {
            vm.navigateTo(Screen.AddEditTask.withArgs(CRUD_Operation.CREATE.ordinal.toString(), "0", listId.toString()))
        },
        list = list ?: emptyList(),
        emptyListMessage = {

        }
    ) { task ->
        TaskItem(
            onClick = {
                vm.navigateTo(Screen.TaskDetails.withArgs(task.taskSetId.toString(), task.id.toString()))
            },
            task = task
        )
    }
}