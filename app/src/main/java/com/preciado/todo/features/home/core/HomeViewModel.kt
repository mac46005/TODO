package com.preciado.todo.features.home.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.preciado.todo.core.models.TODOList
import com.preciado.todo.core.models.TODOListTask
import com.preciado.todo.data.TODOListTasksTable
import com.preciado.todo.data.TODOListsTable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val todoListsTable: TODOListsTable,
    private val todoListTasksTable: TODOListTasksTable
) : ViewModel() {
    private var selectedList: Int = 0

    private val _todoLists: MutableLiveData<List<TODOList>> = MutableLiveData(emptyList())
    val todoLists: LiveData<List<TODOList>> = _todoLists

    private val _todoListTasks: MutableLiveData<List<TODOListTask>> = MutableLiveData(emptyList())
    val todoList: LiveData<List<TODOListTask>> = _todoListTasks

    fun initialize(){
        viewModelScope.launch {
            _todoLists.value = todoListsTable.readAll()
        }
    }

    fun loadTodoList(todoListId: Int){
        selectedList = todoListId
        viewModelScope.launch {
            _todoListTasks.value = todoListTasksTable.readAll()
        }
    }
}