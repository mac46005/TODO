package com.preciado.todo.features.task_details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.preciado.todo.core.common_visuals.components.LoadBar
import com.preciado.todo.core.views.MainView
import com.preciado.todo.features.task_details.core.TaskDetailsViewModel
import kotlinx.coroutines.delay

@Composable
fun TaskDetails(
    navController: NavController,
    listId: Int,
    taskId: Int,
    vm: TaskDetailsViewModel? = hiltViewModel()
){
//    LaunchedEffect(key1 = true){
//        vm!!.initialize(taskId, listId)
//        delay(1000)
//    }
//    val task by vm!!.task.observeAsState()
//    val list by vm!!.list.observeAsState()
//    MainView(
//        navController = navController,
//        subHeader = task!!.taskName,
//        backButtonEnabled = true,
//        popBackStackDestination = "todo_tasks/${task!!.todoList_id}/${list!!.name}",
//        fabEnabled = false
//    ) {
//        //TODO task details
//        //TODO task logger
//    }
    LoadBar()
}