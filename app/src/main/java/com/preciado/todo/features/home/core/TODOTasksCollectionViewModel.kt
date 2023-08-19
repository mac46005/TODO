package com.preciado.todo.features.home.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.preciado.todo.data.TasksTable
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TODOTasksCollectionViewModel @Inject constructor (
    private val tasksTable: TasksTable
): ViewModel() {
    fun loadUncompletedTasks(listId: Int) = tasksTable.getUnCompletedTasks(arrayOf(listId.toString()))
}