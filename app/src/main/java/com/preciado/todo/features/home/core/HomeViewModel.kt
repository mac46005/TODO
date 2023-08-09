package com.preciado.todo.features.home.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.preciado.todo.core.models.TODOList
import com.preciado.todo.core.models.Task
import com.preciado.todo.data.TasksTable
import com.preciado.todo.data.TODOListsTable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val todoListsTable: TODOListsTable,
    private val tasksTable: TasksTable
) : ViewModel() {
    companion object {
        private const val TAG = "HomeViewModel"
    }

    private var _selectedTODOListId: MutableLiveData<Int> = MutableLiveData(0)
    val selectedTODOListId: LiveData<Int> = _selectedTODOListId

//    private val _todoLists: MutableLiveData<List<TODOList>> = MutableLiveData(emptyList())
//    val todoLists: LiveData<List<TODOList>> = _todoLists

    private val _Tasks: MutableLiveData<List<Task>> = MutableLiveData(emptyList())
    val todoList: LiveData<List<Task>> = _Tasks

    private val _isListSelected: MutableLiveData<Boolean> = MutableLiveData(false)
    val isListSelected: LiveData<Boolean> = _isListSelected


    fun initialize() {
    }

    fun loadTodoLists(): Flow<List<TODOList>?> = todoListsTable.readAll()

    fun loadTasks(todoListId: Int): Flow<List<Task>?> {
        _selectedTODOListId.value = todoListId
        _isListSelected.value = true
        return tasksTable.readAll(arrayOf(_selectedTODOListId.value.toString()))
    }

    fun onSelectedTodoList(todoListId: Int){
        _selectedTODOListId.value = todoListId
        _isListSelected.value = true
    }
    fun loadCompletedTodoListTasks(todoListId: Int): Flow<List<Task>?> = if(_selectedTODOListId.value != 0) tasksTable.getCompletedTasks(
        arrayOf(_selectedTODOListId.value.toString())) else emptyFlow()

    fun loadUncompletedTodoListTasks(todoListId: Int): Flow<List<Task>?> = if(_selectedTODOListId.value != 0) tasksTable.getUnCompletedTasks(
        arrayOf(_selectedTODOListId.value.toString())) else emptyFlow()

}