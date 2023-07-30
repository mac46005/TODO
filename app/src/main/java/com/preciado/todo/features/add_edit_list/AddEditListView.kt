package com.preciado.todo.features.add_edit_list

import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
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
    vm: AddEditListViewModel = viewModel<AddEditListViewModel>()
) {
    LaunchedEffect(key1 = true){
        vm.initializeCRUDOperation(crudOperation)
    }


//    val name: String by vm.name
    val name: String by vm.name.observeAsState("")
    TODOTheme() {
        ActionView(
            title = "Add Edit List",
            navController = navController,
            onDone = {
            }
        ) {
            Divider()
            TextField(
                value = name,
                onValueChange = {
                    vm.onNameChange(it)
                },
                placeholder = { Text(text = "Input list name here, please!")},
                maxLines = 1,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color.Transparent
                )
            )
            Divider()
        }
    }
}

@Preview
@Composable
fun PreviewAddEditListView() {
    AddEditListView(
        navController = rememberNavController(),
    )
}