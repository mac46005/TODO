package com.preciado.todo.features.add_edit_list.core

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

    fun onNameChange(newName: String){
        _name.value = newName
    }

    fun onDone(){
        if(crudOperation.equals(CRUDEnum.CREATE)){
            viewModelScope.launch {
                todoListTable.create(_todoList.value!!)
            }
        }
    }



    private fun setTitle(){
        _title.value = if(crudOperation.equals(CRUDEnum.CREATE)) "Add new TODO List" else "Update TODO List name"
    }
    private fun setCRUD_Operation(crudEnum: CRUDEnum){
        crudOperation = crudEnum
    }

    fun initializeCRUDOperation(crudEnum: CRUDEnum, id: Int = 0){
        setCRUD_Operation(crudEnum)
        setTitle()
        if(crudOperation == CRUDEnum.UPDATE){
            setTODOList(id)
        }
    }
    private fun setTODOList(id: Int){
        viewModelScope.launch {
            _todoList.value = todoListTable.read(id)
        }
    }

}