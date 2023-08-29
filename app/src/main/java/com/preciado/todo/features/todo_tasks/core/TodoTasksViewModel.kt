package com.preciado.todo.features.todo_tasks.core

import androidx.lifecycle.ViewModel
import com.preciado.todo.data.TasksTable
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TodoTasksViewModel @Inject constructor(
    private val tasksTable: TasksTable
) : ViewModel() {

    fun loadTasks(listId: Int) = tasksTable.readAll(arrayOf(listId.toString()))

}