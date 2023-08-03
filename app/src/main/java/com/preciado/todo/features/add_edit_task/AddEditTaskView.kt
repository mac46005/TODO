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
import com.preciado.todo.core.common_visuals.components.TransparentTextField
import com.preciado.todo.core.views.ActionView
import com.preciado.todo.data.CRUDEnum
import com.preciado.todo.features.add_edit_task.core.AddEditTaskViewModel
import com.preciado.todo.ui.theme.TODOTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditTaskView(
    navController: NavController,
    crudOperaton: CRUDEnum = CRUDEnum.CREATE,
    listId: Int = 0,
    taskId: Int = 0,
    vm: AddEditTaskViewModel = hiltViewModel()
) {


    LaunchedEffect(key1 = true) {
        vm.initialize()
    }

    val isEnabled by vm.isEnabled.observeAsState(false)
    val taskName by vm.taskname.observeAsState("")
    val taskDetails by vm.taskDetails.observeAsState("")

    TODOTheme() {
        ActionView(
            title = "Title",
            navController = navController,
            submit = {
                navController.popBackStack()
            },
            canceled = {
                navController.popBackStack()
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