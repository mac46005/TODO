package com.preciado.todo.features.add_edit_list.core

import android.database.sqlite.SQLiteConstraintException
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.preciado.todo.core.models.TODOList
import com.preciado.todo.data.CRUDEnum
import com.preciado.todo.data.TODOListTable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class AddEditListViewModel @Inject constructor(
    private val todoListTable: TODOListTable
) : ViewModel() {

    private val _title: MutableLiveData<String> = MutableLiveData("Add New TODO List")
    val title: LiveData<String> = _title

    private val _name: MutableLiveData<String> = MutableLiveData("")
    val name: LiveData<String> = _name

    private val _todoList: MutableLiveData<TODOList> = MutableLiveData(TODOList())
    val todoList: LiveData<TODOList> = _todoList

    private var crudOperation: CRUDEnum = CRUDEnum.CREATE

    private val _isDoneButtonEnabled: MutableLiveData<Boolean> = MutableLiveData(false)
    val isDoneButtonEnabled: LiveData<Boolean> = _isDoneButtonEnabled

    private val _valueExists: MutableLiveData<Boolean> = MutableLiveData(false)
    val valueExists: LiveData<Boolean> = _valueExists


    fun onNameChange(newName: String) {
//        _name.value = newName
        _todoList.value!!.name = newName
    }

    fun onDone() {
        try {
            if (crudOperation.equals(CRUDEnum.CREATE)) {
                checkIfValueAlreadyExists()

                viewModelScope.launch {
                    todoListTable.create(_todoList.value!!)
                }
            }
        } catch (e: SQLiteConstraintException) {

        }
    }


    private fun setTitle() {
        _title.value =
            if (crudOperation.equals(CRUDEnum.CREATE)) "Add new TODO List" else "Update TODO List name"
    }

    private fun setCRUD_Operation(crudEnum: CRUDEnum) {
        crudOperation = crudEnum
    }

    fun checkNameIsNotEmpty() {
        _isDoneButtonEnabled.value = _todoList.value!!.name.isNotEmpty()
        //The below code is equivalent to the above code
//        if(_name.value!!.isNotEmpty()){
//            _isDoneButtonEnabled.value = true
//        }else{
//            _isDoneButtonEnabled.value = false
//        }
    }

    fun initializeCRUDOperation(crudEnum: CRUDEnum, id: Int = 0) {
        setCRUD_Operation(crudEnum)
        setTitle()
        if (crudOperation == CRUDEnum.UPDATE) {
            setTODOList(id)
        }
    }

    private fun setTODOList(id: Int) {
        viewModelScope.launch {
            _todoList.value = todoListTable.read(id)
        }
    }

    private fun checkIfValueAlreadyExists(): Boolean {
        var count = 0
        viewModelScope.launch {
            count = todoListTable.findName(_todoList.value!!.name)
        }

        return count > 0
    }
}