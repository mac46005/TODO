package com.preciado.todo.features.home.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.preciado.todo.core.models.Task
import com.preciado.todo.data.TasksTable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor (
    private val tasksTable: TasksTable
): ViewModel(){
    private val _listId: MutableLiveData<Int> = MutableLiveData(0)
    val listId: LiveData<Int> = _listId





    fun setListId(listId: Int){
        _listId.value = listId
    }

    fun loadUncompletedTasks() = tasksTable.getUnCompletedTasks(arrayOf(_listId.value.toString()))
    fun loadCompletedTasks() = tasksTable.getCompletedTasks(arrayOf(_listId.value.toString()))
}