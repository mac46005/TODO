package com.preciado.todo.features.home.core

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.preciado.todo.core.models.TODOList
import com.preciado.todo.data.TODOListTable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val todoListTable: TODOListTable
) : ViewModel() {

    private val _todoLists: MutableLiveData<List<TODOList>> = MutableLiveData(emptyList())
    val todoList: LiveData<List<TODOList>> = _todoLists
    fun initialize(){
        viewModelScope.launch {
            _todoLists.value = todoListTable.readAll().
        }
    }
}