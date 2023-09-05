package com.preciado.todo.features.add_edit_task

import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.preciado.todo.core.composable_templates.components.TransparentTextField
import com.preciado.todo.core.composables_todo.views.ActionView
import com.preciado.todo.data.CRUDEnum
import com.preciado.todo.features.add_edit_task.core.AddEditTaskViewModel
import com.preciado.todo.ui.theme.TODOTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditTaskView(
    navController: NavController,
    crudEnum: CRUDEnum = CRUDEnum.CREATE,
    listId: Int = 0,
    taskId: Int = 0,
    listName: String = "",
    vm: AddEditTaskViewModel = hiltViewModel()
) {


    LaunchedEffect(key1 = true) {
        vm.initialize(crudEnum, listId,taskId)
    }

    val isEnabled by vm.isEnabled.observeAsState(false)
    val taskName by vm.taskname.observeAsState("")
    val taskDetails by vm.taskDetails.observeAsState("")

    TODOTheme() {
        ActionView(
            title = if(crudEnum == CRUDEnum.CREATE) "Add new task to $listName" else "Edit task from $listName",
            navController = navController,
            submit = {
                vm.submit()
                navController.popBackStack("todo_tasks/$listId/$listName", false)
            },
            backButtonClick = {
                navController.popBackStack("todo_tasks/$listId/$listName",false)
            },
            doneButtonEnabled = isEnabled
        ) {
            Divider()
            TransparentTextField(
                value = taskName,
                onValueChange = {
                                vm.onNameChange(it)
                },
                placeHolder = "Input the name of this task."
            )
            TransparentTextField(
                value = taskDetails,
                onValueChange = {
                                vm.onDetailsChange(it)
                },
                placeHolder = "Input the details of this task. Be as descriptive as you can.",
                singleLine = false,
                maxLines = 5
            )
            Divider()
        }
    }
}

@Preview
@Composable
fun PreviewAddEditTaskView() {
    AddEditTaskView(navController = rememberNavController())
}