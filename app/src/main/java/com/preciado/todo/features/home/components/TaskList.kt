package com.preciado.todo.features.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.preciado.todo.core.models.app_models.Task

private const val TAG = "TaskList"

@Composable
fun TaskList(
    navController: NavController,
    list: List<Task>,
    isEnabled: Boolean,
    itemsState: MutableMap<Int, Boolean>,
    onItemChecked: (Task) -> Unit,
    emptyListMessage: @Composable (() -> Unit)
) {


    AnimatedVisibility(visible = isEnabled) {
        if (list.isEmpty()) {
            emptyListMessage()
        } else {
            LazyColumn() {
                items(list) { task ->
                    itemsState[task.id] = task.isCompleted
                    TaskItem(
                        navController = navController,
                        task = task,
                        onChecked = { checked ->
                            task.isCompleted = checked
                            onItemChecked(task)
                        }
                    )
                }
            }
        }
    }
}