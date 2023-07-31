package com.preciado.todo.features.home.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.preciado.todo.core.models.TODOList
import com.preciado.todo.core.models.TODOListTask
import com.preciado.todo.data.TODOListsTable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val todoListsTable: TODOListsTable
) : ViewModel() {

    private val _todoLists: MutableLiveData<List<TODOList>> = MutableLiveData(emptyList())
    val todoLists: LiveData<List<TODOList>> = _todoLists

    private val _todoList: MutableLiveData<List<TODOListTask>> = MutableLiveData(emptyList())
    val todoList: LiveData<List<TODOListTask>> = _todoList

    fun initialize(){
        viewModelScope.launch {
            _todoLists.value = todoListsTable.readAll()
        }
    }

    fun loadTodoList(todoListId: Int){

    }
}