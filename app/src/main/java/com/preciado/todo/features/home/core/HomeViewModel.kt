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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val todoListsTable: TODOListsTable,
    private val todoListTasksTable: TODOListTasksTable
) : ViewModel() {
    companion object {
        private const val TAG = "HomeViewModel"
    }

    private var _selectedTODOListId: MutableLiveData<Int> = MutableLiveData(0)
    val selectedTODOListId: LiveData<Int> = _selectedTODOListId

//    private val _todoLists: MutableLiveData<List<TODOList>> = MutableLiveData(emptyList())
//    val todoLists: LiveData<List<TODOList>> = _todoLists

    private val _todoListTasks: MutableLiveData<List<TODOListTask>> = MutableLiveData(emptyList())
    val todoList: LiveData<List<TODOListTask>> = _todoListTasks

    private val _isListSelected: MutableLiveData<Boolean> = MutableLiveData(false)
    val isListSelected: LiveData<Boolean> = _isListSelected


    fun initialize() {
    }

    fun loadTodoLists(): Flow<List<TODOList>?> = todoListsTable.readAll()

    fun loadTodoListTasks(todoListId: Int): Flow<List<TODOListTask>?> {
        _selectedTODOListId.value = todoListId
        _isListSelected.value = true
        return todoListTasksTable.readAll(arrayOf(_selectedTODOListId.value.toString()))
    }

    fun loadCompletedTodoListTasks(todoListId: Int): Flow<List<TODOListTask>?> = flow {

    }

    fun loadUncompletedTodoListTasks(todoListId: Int): Flow<List<TODOListTask>?> = flow {

    }

}