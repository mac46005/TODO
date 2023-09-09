package com.preciado.todo.features.add_edit_list

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.preciado.todo.core.composables.composable_templates.components.TransparentTextField
import com.preciado.todo.core.composables.composables_todo.views.FormView
import com.preciado.todo.core.models.app_models.TODOList
import com.preciado.todo.core.models.vm_models.interfaces.IFormVM
import com.preciado.todo.core.models.vm_models.models.FormVM
import com.preciado.todo.core.navigation.Screen
import com.preciado.todo.data.CRUD_Operation
import com.preciado.todo.features.add_edit_list.core.AddEditListFormVM

@Composable
fun  AddEditListFormView(
    navController: NavController,
    crudOperation: CRUD_Operation,
    todoList: TODOList,
    vm: AddEditListFormVM = hiltViewModel()
) {

    vm.navController = navController
    vm.onLoad(crudOperation,todoList)




    FormView<TODOList>(
        navController = navController,
        header = vm.title?: "",
        onBackButtonClicked = {
            vm.onBackButtonClicked()
        },
        submitButton = {
            Text(text = "hello")
        }
    ){
        val model by vm.model!!.observeAsState()
        val name by vm.name!!.observeAsState("")
        TransparentTextField(
            value = name,
            onValueChange = {
                vm.nameChanged(it)
            }
        )
    }
}