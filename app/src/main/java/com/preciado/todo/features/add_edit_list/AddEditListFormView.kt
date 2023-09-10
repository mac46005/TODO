package com.preciado.todo.features.add_edit_list

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
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
    vm: AddEditListFormVM? = hiltViewModel()
) {

    vm!!.onLoad(navController, crudOperation,todoList)

    FormView<TODOList>(
        navController = navController,
        header = vm.title,
        onBackButtonClicked = {
            vm.onBackButtonClicked()
        },
        submitButton = {
            Button(
                modifier = Modifier.align(Alignment.BottomCenter),
                onClick = {
                vm.submitForm()
                }
            ) {
                Text(text = "Submit")
            }
        }
    ){


        val name by vm.name!!.observeAsState("")
        TransparentTextField(
            value = name,
            onValueChange = {
                vm.nameChanged(it)
            },
            placeHolder = "Input the name of this List"
        )



    }
}


@Preview
@Composable
fun PreviewAddEditListFormView(){
    AddEditListFormView(navController = rememberNavController(), crudOperation = CRUD_Operation.CREATE, todoList = TODOList())
}