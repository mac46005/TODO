package com.preciado.todo.features.add_edit_list.core

import android.database.sqlite.SQLiteConstraintException
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.preciado.todo.core.models.TODOList
import com.preciado.todo.data.CRUDEnum
import com.preciado.todo.data.TODOListsTable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditListViewModel @Inject constructor(
    private val todoListsTable: TODOListsTable
) : ViewModel() {

    private var crudOperation: CRUDEnum = CRUDEnum.CREATE

    private val _title: MutableLiveData<String> = MutableLiveData("Add new TODO List")
    val title: LiveData<String> = _title

    private val _id: MutableLiveData<Int> = MutableLiveData(0)
    val id: LiveData<Int> = _id

    private val _todoListName: MutableLiveData<String> = MutableLiveData("")
    val todoListName: LiveData<String> = _todoListName

    private val _isEnabled: MutableLiveData<Boolean> = MutableLiveData(false)
    val isEnabled: LiveData<Boolean> = _isEnabled

    fun submit(){
        try {
            viewModelScope.launch {
                todoListsTable.create(TODOList(name = _todoListName.value!!))
            }
            onCleared()
        }catch (e: SQLiteConstraintException){
            throw e
        }
    }

    fun onCanceled(){
        onCleared()
    }
    fun onTodoListNameChange(newName: String){
        _todoListName.value = newName
    }
    fun doesValueExistsInDatabase(): Boolean{
        return false
    }

    fun isNameNotEmpty(){
        _isEnabled.value = _todoListName.value!!.isNotEmpty()
    }
    fun initializeCRUDOperation(crudOperation: CRUDEnum, tableId: Int){
        this.crudOperation = crudOperation
        if(this.crudOperation.equals(CRUDEnum.UPDATE)){
            viewModelScope.launch{
                var todoList = todoListsTable.read(tableId)
                if (todoList != null) {
                    _id.value = todoList.id
                }
                if (todoList != null) {
                    _todoListName.value = todoList.name
                }
            }
        }
    }
}