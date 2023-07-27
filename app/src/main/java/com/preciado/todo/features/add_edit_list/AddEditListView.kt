package com.preciado.todo.features.add_edit_list

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.preciado.todo.core.views.ActionView
import com.preciado.todo.data.CRUDEnum
import com.preciado.todo.features.add_edit_list.core.AddEditListViewModel
import com.preciado.todo.ui.theme.TODOTheme

@Composable
fun AddEditListView(
    navController: NavController,
    crudOperation: CRUDEnum = CRUDEnum.CREATE,
    vm: AddEditListViewModel
){
    TODOTheme() {
        ActionView(title = "Add Edit List", navController = navController)
    }
}

@Preview
@Composable
fun PreviewAddEditListView(){
    AddEditListView(
        navController = rememberNavController(),
        vm = viewModel()
    )
}