package com.preciado.todo.features.todo_tasks

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.preciado.todo.core.views.MainView
import com.preciado.todo.data.CRUDEnum
import com.preciado.todo.features.home.components.BigMessage
import com.preciado.todo.features.todo_tasks.components.TaskItem
import com.preciado.todo.features.todo_tasks.core.TodoTasksViewModel

@Composable
fun TodoTasks(
    navController: NavController,
    listId: Int,
    listName: String,
    vm: TodoTasksViewModel? = hiltViewModel()
){
    val list by vm!!.loadTasks(listId).collectAsStateWithLifecycle(initialValue = emptyList())

    MainView(
        navController = navController,
        subHeader = "$listName",
        popBackStackDestination = "todo_lists",
        onFabClickedDestination = "add_edit_task/${CRUDEnum.CREATE.ordinal}/$listId/$listName/0"
    ) {
        if (list!!.isEmpty()){
            BigMessage(message = "No tasks")
        }else{
            LazyColumn(){
                items(list!!){item ->
                    TaskItem(navController = navController, task = item){
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewTodoTasks(){
    TodoTasks(navController = rememberNavController(), listId = 0, listName = "Name", vm = null)
}