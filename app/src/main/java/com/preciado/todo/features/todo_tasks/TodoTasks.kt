package com.preciado.todo.features.todo_tasks

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.preciado.todo.core.views.MainView
import com.preciado.todo.data.CRUDEnum
import com.preciado.todo.features.todo_tasks.core.TodoTasksViewModel

@Composable
fun TodoTasks(
    navController: NavController,
    listId: Int,
    listName: String,
    vm: TodoTasksViewModel? = hiltViewModel()
){

    MainView(
        navController = navController,
        subHeader = "$listName",
        popBackStackDestination = "todo_lists",
        onFabClickedDestination = "add_edit_list_task/${CRUDEnum.CREATE.ordinal}/$listId/0"
    ) {

    }
}

@Preview
@Composable
fun PreviewTodoTasks(){
    TodoTasks(navController = rememberNavController(), listId = 0, listName = "Name", vm = null)
}