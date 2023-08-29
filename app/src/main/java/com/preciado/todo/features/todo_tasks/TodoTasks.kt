package com.preciado.todo.features.todo_tasks

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.preciado.todo.core.views.MainView
import com.preciado.todo.data.CRUDEnum

@Composable
fun TodoTasks(
    navController: NavController,
    listId: Int
){
    LaunchedEffect(key1 = true){

    }

    MainView(
        navController = navController,
        popBackStackDestination = "todo_lists",
        onFabClickedDestination = "add_edit_list_task/${CRUDEnum.CREATE.ordinal}/$listId/0"
    ) {

    }
}