package com.preciado.todo.features.add_edit_list

import android.content.Context
import android.widget.Toast
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.preciado.todo.core.helpers.CapitalizeWords
import com.preciado.todo.core.models.TODOList
import com.preciado.todo.core.views.ActionView
import com.preciado.todo.data.CRUDEnum
import com.preciado.todo.data.TODOListTable
import com.preciado.todo.features.add_edit_list.core.AddEditListViewModel
import com.preciado.todo.ui.theme.TODOTheme
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditListView(
    navController: NavController,
    crudOperation: CRUDEnum = CRUDEnum.CREATE,
    tableId: Int = 0,
    vm: AddEditListViewModel = viewModel<AddEditListViewModel>()
) {
    LaunchedEffect(key1 = true){
        vm.initializeCRUDOperation(crudOperation, tableId)
    }

    val title: String by vm.title.observeAsState("Add new List")
    val name: String by vm.name.observeAsState("")
    val todoList: TODOList by vm.todoList.observeAsState(TODOList())
    val isEnabled: Boolean by vm.isDoneButtonEnabled.observeAsState(false)

    TODOTheme() {
        ActionView(
            title = title,
            navController = navController,
            onDone = {
                vm.onDone()
                navController.popBackStack()
            },
            doneButtonEnabled = isEnabled
        ) {
            Divider()
            TextField(
                value = todoList.name,
                onValueChange = { value ->
                    vm.onNameChange(CapitalizeWords.getCaptilizedSentence(value))
                    vm.checkNameIsNotEmpty()
                },
                placeholder = { Text(text = "Input list name here, please!")}
                ,
                maxLines = 1,
                textStyle = TextStyle(),
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