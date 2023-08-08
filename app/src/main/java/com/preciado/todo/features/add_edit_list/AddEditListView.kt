package com.preciado.todo.features.add_edit_list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.preciado.todo.core.common_visuals.components.TransparentTextField
import com.preciado.todo.core.helpers.CapitalizeWords
import com.preciado.todo.core.views.ActionView
import com.preciado.todo.data.CRUDEnum
import com.preciado.todo.features.add_edit_list.core.AddEditListViewModel
import com.preciado.todo.ui.theme.TODOTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditListView(
    navController: NavController,
    crudOperation: CRUDEnum = CRUDEnum.CREATE,
    tableId: Int = 0,
    vm: AddEditListViewModel? = hiltViewModel()
) {
    LaunchedEffect(key1 = true) {
        vm!!.initializeCRUDOperation(crudOperation, tableId)
    }

    val title: String by vm!!.title.observeAsState("Add new List")
    val todoListName: String by vm!!.todoListName.observeAsState("")
    val isEnabled: Boolean by vm!!.isEnabled.observeAsState(false)

    var visible by remember {
        mutableStateOf(false)
    }
    var errorMessage by remember {
        mutableStateOf("")
    }


    TODOTheme() {
        ActionView(
            title = title,
            navController = navController,
            submit = {
                try {
                    vm!!.submit()
                    visible = false
                    navController.popBackStack()
                } catch (e: Exception) {
                    errorMessage = e.message.toString()
                    visible = true
                }

            },
            backButtonClick = {
                vm!!.onCanceled()
            },
            doneButtonEnabled = isEnabled
        ) {

            Divider()
            TransparentTextField(
                value = todoListName?: "null",
                onValueChange = { value ->
                    vm!!.onTodoListNameChange(CapitalizeWords.getCaptilizedSentence(value))
                    vm.isNameNotEmpty()
                },
                placeHolder = "Input list name here, please!"
            )
            Divider()
            AnimatedVisibility(visible = visible) {
                Text(text = errorMessage)
            }
        }
    }
}


@Preview
@Composable
fun PreviewAddEditListView() {
    AddEditListView(
        navController = rememberNavController(),
        vm = null
    )
}