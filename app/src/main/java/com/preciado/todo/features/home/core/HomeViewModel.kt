package com.preciado.todo.features.home.core

import androidx.lifecycle.ViewModel
import com.preciado.todo.data.TODOListsTable
import com.preciado.todo.data.TasksTable
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val todoListsTable: TODOListsTable,
    private val tasksTable: TasksTable
) : ViewModel() {

}