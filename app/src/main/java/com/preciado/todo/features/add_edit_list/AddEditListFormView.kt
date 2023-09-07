package com.preciado.todo.features.add_edit_list

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.preciado.todo.core.composables.composable_templates.views.FormTemplate
import com.preciado.todo.core.composables.composables_todo.views.FormView
import com.preciado.todo.core.models.app_models.TODOList

@Composable
fun  AddEditListFormView(
    navController: NavController,
    todoList: TODOList
) {

    FormView<TODOList>(
        navController = navController,
        header = "",
        onBackButtonClicked = { /*TODO*/ },
        submitButton = {
            Text(text = "hello")
        }
    ){

    }
}