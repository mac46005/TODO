package com.preciado.todo.features.todo_lists.core

import androidx.lifecycle.ViewModel
import com.preciado.todo.data.TODOListsTable
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TodoListsViewModel @Inject constructor(
    private var todoListsTable: TODOListsTable
) : ViewModel() {
    fun loadLists() = todoListsTable.readAll()
}