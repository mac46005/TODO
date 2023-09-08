package com.preciado.todo.features.add_edit_list.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.preciado.todo.core.models.app_models.TODOList
import com.preciado.todo.core.models.vm_models.interfaces.IFormVM
import com.preciado.todo.core.models.vm_models.models.FormVM
import com.preciado.todo.core.models.vm_models.models.VM
import com.preciado.todo.core.navigation.Screen
import com.preciado.todo.data.CRUD_Operation
import com.preciado.todo.data.TODOListsTable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditListFormVM @Inject constructor(
    private var todoListsTable: TODOListsTable
) : FormVM<TODOList>() {
    override fun submitForm() {
        when(crudOperation){
            CRUD_Operation.CREATE -> {}
            CRUD_Operation.UPDATE -> {}
            else -> {

            }
        }
    }

    override fun onLoad(vararg args: Any) {
        crudOperation = args[0] as CRUD_Operation
        val todoList = args[1] as TODOList
        when(crudOperation){
            CRUD_Operation.CREATE -> {
                title = "Add new List"
                _model.value = todoList
            }
            CRUD_Operation.UPDATE -> {
                viewModelScope.launch {
                    _model.value = todoListsTable.read(todoList.id)
                    title = "Edit " + _model.value!!.name
                }
            }
            else -> {

            }
        }
    }

    override fun onBackButtonClicked() {
        navController!!.navigate(Screen.TODOLists.fullRoute())
    }

    fun nameChanged(name: String){
        _model.value!!.name = name
    }

}