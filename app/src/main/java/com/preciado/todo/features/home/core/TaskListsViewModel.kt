package com.preciado.todo.features.home.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.preciado.todo.core.models.app_models.Task
import com.preciado.todo.data.TasksTable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskListsViewModel @Inject constructor(
    private val tasksTable: TasksTable
)
    : ViewModel() {

    var itemsCheckedMap = mutableMapOf<Int, Boolean>()

    fun updateTask(task: Task){
        viewModelScope.launch {
            tasksTable.update(task)
        }
    }
    fun loadIncompleteTasks(listId: Int) = tasksTable.getInCompleteTasks(arrayOf(listId.toString()))
    fun loadCompleteTasks(listId: Int) = tasksTable.getCompletedTasks(arrayOf(listId.toString()))
}