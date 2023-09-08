package com.preciado.todo.features.add_edit_list.core

import androidx.lifecycle.viewModelScope
import com.preciado.todo.core.models.app_models.TODOList
import com.preciado.todo.core.models.vm_models.models.FormVM
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




    override fun onLoad(vararg args: Any) {
        crudOperation = args[0] as CRUD_Operation
        val model = args[1] as TODOList
        when(crudOperation){
            CRUD_Operation.CREATE -> {
                setModel(model)
                title = "Add new List"
            }
            CRUD_Operation.UPDATE -> {
                viewModelScope.launch {
                    setModel(todoListsTable.read(model.id)!!)
                    title = "Edit " + getModel().name
                }
            }
            else -> {

            }
        }
    }

    override fun onBackButtonClicked() {
        navController!!.popBackStack(
            Screen.TODOLists.fullRoute(),
            true,
            false
        )
    }


    override fun submitForm() {
        viewModelScope.launch {
            when(crudOperation){
                CRUD_Operation.CREATE -> todoListsTable.create(getModel())
                CRUD_Operation.UPDATE -> todoListsTable.update(getModel())
                else -> {}
            }.also {
                onBackButtonClicked()
            }
        }

    }
}