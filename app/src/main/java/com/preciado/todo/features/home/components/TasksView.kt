package com.preciado.todo.features.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
    LaunchedEffect(key1 = listId){
        vm.setListId(listId)
    }
    var uncompletedTasksState = vm.loadUncompleteTasks().collectAsState(initial = emptyList())
    var completedTasksState = vm.loadCompleteTasks().collectAsState(initial = emptyList())

    var isCompletedTasksVisible = remember {
        mutableStateOf(false)
    }
    var arrangmentState = remember {
        mutableStateOf(Arrangement.SpaceBetween)
    }
    var fractionState = remember {
        mutableStateOf(
            arrayOf(
                1f,
                0f
            )
        )
    }

    TODOTheme() {
        Column(
            verticalArrangement = arrangmentState.value
        ) {
            //Uncompleted Tasks
            TasksList(
                modifier = Modifier.weight(fractionState.value[0]),
                navController = navController,
                tasks = uncompletedTasksState.value
            )


            if (completedTasksState.value.isNotEmpty()) {
                fractionState.value[0] = .95f
                fractionState.value[1] = .5f

                Column(
                    modifier = Modifier.weight(fractionState.value[1])
                ) {
                    CompletedTasksTab {
                        if (isCompletedTasksVisible.value == false){
                            isCompletedTasksVisible.value = true
                            fractionState.value[0] = 1f
                            fractionState.value[1] = 1f
                        }else{
                            isCompletedTasksVisible.value = false
                            fractionState.value[0] = .95f
                            fractionState.value[1] = .5f
                        }
                    }
                    AnimatedVisibility(visible = isCompletedTasksVisible.value) {
                        TasksList(
                            navController = navController,
                            tasks = completedTasksState.value
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewTasksView() {
}