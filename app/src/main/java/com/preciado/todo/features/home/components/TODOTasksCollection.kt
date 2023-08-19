package com.preciado.todo.features.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.preciado.todo.features.home.core.TODOTasksCollectionViewModel

@Composable
fun TODOTasksCollection(
    listId: Int,
    vm: TODOTasksCollectionViewModel = hiltViewModel()
){
    val uncompletedTasksState by vm.loadUncompletedTasks(listId = listId).collectAsState(initial = emptyList())


    AnimatedVisibility(visible = uncompletedTasksState.isEmpty()) {
        BigMessage(message = "No Tasks!")
    }
    AnimatedVisibility(visible = uncompletedTasksState.isNotEmpty()) {
        TaskList(taskList = uncompletedTasksState)
    }

}