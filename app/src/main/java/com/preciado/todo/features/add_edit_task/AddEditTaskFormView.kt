package com.preciado.todo.features.add_edit_task

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.preciado.todo.core.models.app_models.Task
import com.preciado.todo.data.CRUD_Operation

@Composable
fun AddEditTaskFormView(
    navController: NavController,
    crudOperation: CRUD_Operation = CRUD_Operation.CREATE,
    task: Task
    ){

}