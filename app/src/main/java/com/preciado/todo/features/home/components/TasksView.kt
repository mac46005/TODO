package com.preciado.todo.features.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.preciado.todo.core.models.Task
import com.preciado.todo.features.home.core.TasksViewModel
import com.preciado.todo.ui.theme.TODOTheme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow

@Composable
fun TasksView(
    navController: NavController,
    listId: Int = 0,
    vm: TasksViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = true){
        vm.setListId(listId)
    }


    val uncompletedTasks by vm.loadUncompletedTasks().collectAsState(initial = emptyList())
    val isUTEmpty = remember {
        mutableStateOf(uncompletedTasks.isEmpty())
    }
    
    val completedTasks by vm.loadCompletedTasks().collectAsState(initial = emptyList())
    


    var arrangmentState = remember {
        mutableStateOf(Arrangement.SpaceBetween)
    }



    TODOTheme() {
        Column(
            verticalArrangement = arrangmentState.value
        ) {
                TasksList(navController = navController, tasks = uncompletedTasks)
        }
    }
}

@Preview
@Composable
fun PreviewTasksView() {
}