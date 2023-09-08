package com.preciado.todo.features.todo_lists

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.preciado.todo.core.composables.composables_todo.components.EmptyListMessage
import com.preciado.todo.core.composables.composables_todo.views.TODOListView
import com.preciado.todo.core.models.app_models.TODOList
import com.preciado.todo.core.navigation.Screen
import com.preciado.todo.data.CRUD_Operation
import com.preciado.todo.features.todo_lists.components.ListItem
import com.preciado.todo.features.todo_lists.core.TodoListVM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListsView(
    navController: NavController,
    vm: TodoListVM? = hiltViewModel()
){
    vm!!.navController = navController

    val list by vm.loadList().collectAsStateWithLifecycle(initialValue = emptyList())

    TODOListView<TODOList>(
        title = "Your Lists",
        backButtonVisible = false,
        onFloatingActionButtonClicked = {
            vm.navigateTo(Screen.AddEditList.withArgs(CRUD_Operation.CREATE.ordinal.toString(), "0"))
        },
        list = list?: emptyList(),
        emptyListMessage = {
            EmptyListMessage()
        }
    ) {item ->
        ListItem(name = item.name) {
            vm.onItemSelected(item)
        }

    }
}

@Preview
@Composable
fun PreviewTodoLists(){
    TodoListsView(
        navController = rememberNavController(),
        vm = null
    )
}