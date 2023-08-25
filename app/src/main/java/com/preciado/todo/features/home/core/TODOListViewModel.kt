package com.preciado.todo.features.home.core

import androidx.lifecycle.ViewModel
import com.preciado.todo.core.models.TODOList
import com.preciado.todo.data.TODOListsTable
import com.preciado.todo.data.TasksTable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class TODOListViewModel @Inject constructor(
    private val todoListsTable: TODOListsTable
) : ViewModel()  {
    fun loadTodoLists(): Flow<List<TODOList>?> = todoListsTable.readAll()
}