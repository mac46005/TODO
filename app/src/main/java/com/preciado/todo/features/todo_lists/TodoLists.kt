package com.preciado.todo.features.todo_lists

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.preciado.todo.core.views.BaseView
import com.preciado.todo.core.views.MainView
import com.preciado.todo.data.CRUDEnum
import com.preciado.todo.features.todo_lists.components.Item
import com.preciado.todo.features.todo_lists.core.TodoListsViewModel
import com.preciado.todo.ui.theme.TODOTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoLists(
    navController: NavController,
    vm: TodoListsViewModel? = hiltViewModel()
){
    val list by vm!!.loadLists().collectAsStateWithLifecycle(initialValue = emptyList())
    MainView(
        navController = navController,
        subHeader = "Your Lists",
        backButtonEnabled = false,
        onFabClickedDestination = "add_edit_list/${CRUDEnum.CREATE.ordinal}/0"
    ) {
        LazyColumn(){
            items(list!!){ item ->
                Item(name = item.name){
                    navController.navigate("todo_tasks/${item.id}")
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewTodoLists(){
    TodoLists(
        navController = rememberNavController(),
        vm = null
    )
}