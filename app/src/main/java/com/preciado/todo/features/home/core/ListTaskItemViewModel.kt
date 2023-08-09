package com.preciado.todo.features.home.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.preciado.todo.core.models.Task
import com.preciado.todo.data.TODOListTasksTable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListTaskItemViewModel @Inject constructor(
    private val listTasksTable: TODOListTasksTable
): ViewModel() {

    fun onCheckChanged(task: Task){
        viewModelScope.launch {
            task.isCompleted = true
            listTasksTable.update(task)
        }
    }
}