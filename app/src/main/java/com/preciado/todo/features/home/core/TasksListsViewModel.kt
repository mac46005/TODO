package com.preciado.todo.features.home.core

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.preciado.todo.core.models.Task
import com.preciado.todo.data.TasksTable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksListsViewModel @Inject constructor (
    private val tasksTable: TasksTable
): ViewModel() {
    companion object{
        private const val TAG = "TasksListsViewModel"
    }


    fun loadUncompletedTasks(listId: Int) = tasksTable.getUnCompletedTasks(arrayOf(listId.toString()))
    fun loadCompletedTasks(listId: Int) = tasksTable.getCompletedTasks(arrayOf(listId.toString()))

    fun itemChecked(task: Task){
        viewModelScope.launch {
            Log.i(TAG, "itemChecked: taskName: ${task.taskName} isComplete: ${task.isCompleted}")
            task.isCompleted = task.isCompleted == false
            Log.i(TAG, "itemChecked: taskName: ${task.taskName} isComplete: ${task.isCompleted}")

            tasksTable.update(task)
        }
    }
}