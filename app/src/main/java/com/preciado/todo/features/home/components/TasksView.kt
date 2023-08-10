package com.preciado.todo.features.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.preciado.todo.core.models.Task
import com.preciado.todo.ui.theme.TODOTheme

@Composable
fun TasksView(
    navController: NavController,
    uncompletedTasks: List<Task> = emptyList(),
    completedTasks: List<Task> = emptyList()
) {
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
                tasks = uncompletedTasks
            )


            if (completedTasks.isNotEmpty()) {
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
                            tasks = completedTasks
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
    TasksView(navController = rememberNavController())
}