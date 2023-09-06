package com.preciado.todo.features.todo_tasks.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.preciado.todo.core.models.app_models.Task
import com.preciado.todo.data.TasksTable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class TodoTasksViewModel @Inject constructor(
    private val tasksTable: TasksTable
) : ViewModel() {





    fun loadTasks(listId: Int) = tasksTable.readAll(arrayOf(listId.toString()))
    fun updateTask(task: Task){
        viewModelScope.launch {
            task.completedOn = LocalDateTime.now()
            tasksTable.update(task)
        }
    }

    fun onBackButtonClicked(navController: NavController){
        //navController.navigate("add_edit_task/${CRUDEnum.CREATE.ordinal}/$listId/$listName/0")
    }
}