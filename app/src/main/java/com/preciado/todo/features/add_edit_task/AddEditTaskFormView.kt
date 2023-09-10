package com.preciado.todo.features.add_edit_task

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.preciado.todo.core.composables.composable_templates.components.TransparentTextField
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

    vm!!.onLoad(navController,crudOperation, task)

    FormView<Task>(
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
    ) {

        TransparentTextField(value = "", onValueChange = {})





    }
}