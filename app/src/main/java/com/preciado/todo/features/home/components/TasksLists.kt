package com.preciado.todo.features.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.preciado.todo.features.home.core.TasksListsViewModel

@Composable
fun TasksLists(
    navController: NavController,
    listId: Int,
    vm: TasksListsViewModel = hiltViewModel()
){


    Column(modifier = Modifier.fillMaxHeight()) {


        val uncompletedTasksState by vm.loadUncompletedTasks(listId = listId).collectAsState(initial = emptyList())
        val completedTasksState by vm.loadCompletedTasks(listId = listId).collectAsState(initial = emptyList())

        LazyColumn(
        ){
            items(uncompletedTasksState){task ->
                var taskState = remember {
                    mutableStateOf(task)
                }
                TaskItem(
                    navController = navController,
                    task = taskState.value,
                    onCheckChanged = {
                        vm.itemChecked(taskState.value)
                    }
                )
            }
        }
    }

}