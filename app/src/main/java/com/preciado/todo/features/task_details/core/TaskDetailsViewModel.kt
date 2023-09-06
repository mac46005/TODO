package com.preciado.todo.features.task_details.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.preciado.todo.core.models.app_models.TODOList
import com.preciado.todo.core.models.app_models.Task
import com.preciado.todo.data.TODOListsTable
import com.preciado.todo.data.TasksTable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskDetailsViewModel @Inject constructor(
    private val tasksTable: TasksTable,
    private val todoListsTable: TODOListsTable
) : ViewModel() {

    private val _task: MutableLiveData<Task> = MutableLiveData()
    var task: LiveData<Task> = _task

    private val _list: MutableLiveData<TODOList> = MutableLiveData()
    var list: LiveData<TODOList> = _list

    fun initialize(vararg args: Any){
        viewModelScope.launch {
            _task.value = tasksTable.read(Integer.parseInt(args[0].toString()), arrayOf(args[1].toString()))
            _list.value = todoListsTable.read(Integer.parseInt(args[1].toString()))
        }
    }

    fun onBackButtonClicked(navController: NavController): Unit{
        navController.navigate("todo_tasks/${_list.value!!.id}/${_list.value!!.name}")
    }

}