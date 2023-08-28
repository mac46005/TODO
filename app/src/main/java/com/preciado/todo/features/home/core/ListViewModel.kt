package com.preciado.todo.features.home.core

import androidx.lifecycle.ViewModel
import com.preciado.todo.data.TODOListsTable
import com.preciado.todo.data.TasksTable
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class ListViewModel(
    private val todoListTable: TODOListsTable
): ViewModel() {
    fun loadListTable() = todoListTable.readAll()
}