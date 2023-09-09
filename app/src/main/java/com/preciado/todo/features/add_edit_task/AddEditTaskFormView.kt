package com.preciado.todo.features.add_edit_task

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.preciado.todo.core.composables.composables_todo.views.FormView
import com.preciado.todo.core.models.app_models.Task
import com.preciado.todo.data.CRUD_Operation
import com.preciado.todo.features.add_edit_task.core.AddEditTaskFormVM

@Composable
fun AddEditTaskFormView(
    navController: NavController,
    crudOperation: CRUD_Operation = CRUD_Operation.CREATE,
    task: Task,
    vm: AddEditTaskFormVM? = hiltViewModel()
    ){

    vm!!.onLoad(crudOperation,crudOperation, task)

    FormView<Task>(
        navController = navController,
        header = vm.title,
        onBackButtonClicked = {

        },
        submitButton = {

        }
    ) {

    }
}