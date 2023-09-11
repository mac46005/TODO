package com.preciado.todo.features.task_list

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.preciado.todo.core.composables.composables_todo.views.TODOListView
import com.preciado.todo.core.models.app_models.interfaces.ITaskSet
import com.preciado.todo.core.models.app_models.models.Task
import com.preciado.todo.core.navigation.Screen
import com.preciado.todo.data.CRUD_Operation
import com.preciado.todo.features.task_list.components.TaskItem
import com.preciado.todo.features.task_list.core.TaskListVM

private const val TAG = "TaskListView"
@Composable
fun TaskListView(
    navController: NavController,
    taskSet: ITaskSet<Int>,
    vm: TaskListVM? = hiltViewModel()
) {


    vm!!.onLoad(navController, taskSet)

    val list by vm.loadList(taskSet.id).collectAsStateWithLifecycle(initialValue = emptyList())

    TODOListView<Task>(
        title = vm.title,
        backButtonVisible = true,
        onBackButtonClicked = {
            vm.onBackButtonClicked()
        },
        onFloatingActionButtonClicked = {
            vm.navigateTo(Screen.AddEditTask.withArgs(CRUD_Operation.CREATE.ordinal.toString(), "0", taskSet.id.toString()))
        },
        list = list ?: emptyList(),
        emptyListMessage = {

        }
    ) { task ->
        Log.i(TAG, "TaskListView: $task")
        TaskItem(
            onClick = {
                vm.navigateTo(Screen.TaskDetails.withArgs(task.taskSet.id.toString(), task.id.toString()))
            },
            task = task
        )
    }
}