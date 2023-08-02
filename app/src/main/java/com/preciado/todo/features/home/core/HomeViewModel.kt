package com.preciado.todo.features.home.core

import android.util.Log
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
    companion object{
        private const val TAG = "HomeViewModel"
    }


    private var selectedList: Int = 0

    private var _selectedTODOListId: MutableLiveData<Int> = MutableLiveData(0)
    val selectedTODOListId: LiveData<Int> = _selectedTODOListId

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
        _selectedTODOListId.value = todoListId
        viewModelScope.launch {
            Log.i(TAG, "loadTodoList: attempting to load todoListId $_selectedTODOListId")
            _todoListTasks.value = todoListTasksTable.readAll()
        }
    }
}