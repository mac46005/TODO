package com.preciado.todo.features.home.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.preciado.todo.core.models.TODOList
import com.preciado.todo.data.TODOListsTable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val todoListsTable: TODOListsTable,
) : ViewModel() {


    companion object {
        private const val TAG = "HomeViewModel"
    }

    private var _selectedTODOListId: MutableLiveData<Int> = MutableLiveData(0)
    val selectedTODOListId: LiveData<Int> = _selectedTODOListId

    private val _isListSelected: MutableLiveData<Boolean> = MutableLiveData(false)
    val isListSelected: LiveData<Boolean> = _isListSelected

    fun loadTodoLists(): Flow<List<TODOList>?> = todoListsTable.readAll()

    fun onListSelected(todoListId: Int) {
        _selectedTODOListId.value = todoListId
        _isListSelected.value = true
    }


}