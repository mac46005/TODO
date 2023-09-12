package com.preciado.todo.features.task_list

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.preciado.todo.core.composables.composables_todo.components.EmptyListMessage
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




    val fabVisible = remember {
        mutableStateOf(false)
    }
    if(list!!.isEmpty()){
        fabVisible.value = false
    }else{
        fabVisible.value = true
    }


    TODOListView<Task>(
        title = vm.title,
        backButtonVisible = true,
        onBackButtonClicked = {
            vm.onBackButtonClicked()
        },
        floatingActionButtonVisible = fabVisible.value,
        onFloatingActionButtonClicked = {
            vm.navigateTo(Screen.AddEditTask.withArgs(CRUD_Operation.CREATE.ordinal.toString(), "0", taskSet.id.toString()))
        },
        list = list ?: emptyList(),
        emptyListMessage = {
            EmptyListMessage(
                message = "There are no Tasks!\nHow about we add a new task!"
            ){
                Button(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(20.dp),
                    onClick = {
                    vm.navigateTo(Screen.AddEditTask.withArgs(CRUD_Operation.CREATE.ordinal.toString(), "0", taskSet.id.toString()))
                }) {
                    Text(text = "Create Task")
                }
            }
        }
    ) { task ->
        Log.i(TAG, "TaskListView: $task")
        Log.i(TAG, "TaskListView: isComplete: ${task.isCompleted}")
        TaskItem(
            onClick = {
                vm.navigateTo(Screen.TaskDetails.withArgs(task.taskSet.id.toString(), task.id.toString()))
            },
            task = task,
            oncheckedChanged = {
                task.isCompleted = it
                vm.updateItem(task)
            }
        )
    }
}