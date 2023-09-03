package com.preciado.todo.features.task_details

import androidx.compose.material3.Text
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

    LaunchedEffect(key1 = true){
        vm!!.initialize(listId, taskId)
    }

    val listState by vm!!.list.observeAsState(initial = null)
    val taskState by vm!!.list.observeAsState(initial = null)



    MainView(
        navController = navController,
        onBackButtonClicked = vm!!::onBackButtonClicked
    ) {
        if(listState == null && taskState == null){
            LoadBar()
        }else{
            Text(text = listState!!.name)
            Text(text = taskState!!.name)
        }
    }
}