package com.preciado.todo.features.home.core

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.preciado.todo.core.models.TODOListTask
import com.preciado.todo.data.TODOListTasksTable
import com.preciado.todo.data.interfaces.IUpdate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListTaskItemViewModel @Inject constructor(
    private val listTasksTable: TODOListTasksTable
): ViewModel() {

    fun onCheckChanged(todoListTask: TODOListTask){
        viewModelScope.launch {
            todoListTask.isCompleted = true
            listTasksTable.update(todoListTask)
        }
    }
}