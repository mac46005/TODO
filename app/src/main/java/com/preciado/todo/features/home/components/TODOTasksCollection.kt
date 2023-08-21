package com.preciado.todo.features.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.preciado.todo.features.home.core.TODOTasksCollectionViewModel

@Composable
fun TODOTasksCollection(
    navController: NavController,
    listId: Int,
    vm: TODOTasksCollectionViewModel = hiltViewModel()
){
    val uncompletedTasksState by vm.loadUncompletedTasks(listId = listId).collectAsState(initial = emptyList())
    val completedTasksState by vm.loadCompletedTasks(listId = listId).collectAsState(initial = emptyList())

    Column(modifier = Modifier.fillMaxHeight()) {
//        if(uncompletedTasksState.isEmpty()){
//            BigMessage(message = "You have no tasks!")
//        }else{
//            TaskList(navController = navController, taskList = uncompletedTasksState)
//        }
        TaskList(navController = navController, taskList = uncompletedTasksState)
    }

}