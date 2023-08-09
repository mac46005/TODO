package com.preciado.todo.features.home.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.preciado.todo.core.models.Task
import com.preciado.todo.data.TasksTable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskItemViewModel @Inject constructor(
    private val tasksTable: TasksTable
): ViewModel() {

    fun onCheckChanged(task: Task){
        viewModelScope.launch {
            task.isCompleted = true
            tasksTable.update(task)
        }
    }
}