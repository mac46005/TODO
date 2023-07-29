package com.preciado.todo.features.add_edit_list.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.preciado.todo.core.models.TODOList
import com.preciado.todo.data.TODOListTable
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddEditListViewModel @Inject constructor(
    private val todoListTable: TODOListTable
) : ViewModel() {

    private val _name: MutableLiveData<String> = MutableLiveData("")
    val name: LiveData<String> = _name

    private val _todoList: MutableLiveData<TODOList> = MutableLiveData(TODOList())
    val todoList: LiveData<TODOList> = _todoList
    fun onNameChange(newName: String){
        _name.value = newName
    }

    fun onDone(){

    }

    suspend fun getTODOList(id: Int){
        _todoList.value = todoListTable.read(id)
    }
}