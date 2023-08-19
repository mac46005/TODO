package com.preciado.todo.features.home.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.preciado.todo.data.TasksTable
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class TODOTasksCollectionViewModel(
    private val tasksTable: TasksTable
): ViewModel() {
    private val _listId: MutableLiveData<Int> = MutableLiveData()
    val listId: LiveData<Int> = _listId


    fun setListId(listId: Int){
        _listId.value = listId
    }

    fun loadUncompletedTasks() = tasksTable.getUnCompletedTasks(arrayOf(_listId.value.toString()))
}