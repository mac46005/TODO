package com.preciado.todo.features.home.core

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.preciado.todo.core.models.app_models.Task
import com.preciado.todo.data.TasksTable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskItemViewModel @Inject constructor(
    private val tasksTable: TasksTable
) : ViewModel() {

    
    companion object{
        private const val TAG = "TaskItemViewModel"
    }
    
    fun onCheckChanged(task: Task){
        viewModelScope.launch {
            task.isCompleted = task.isCompleted == false
            Log.i(TAG, "onCheckChanged: task.isCompleted: ${task.isCompleted}")
            tasksTable.update(task)
        }
    }
}