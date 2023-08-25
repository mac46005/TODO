package com.preciado.todo.features.home.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.preciado.todo.features.home.core.TaskListsViewModel

@Composable
fun TaskListsView(
    navController: NavController,
    listId: Int,
    isIncompleteTaskListEnabled: Boolean,
    isCompletedTaskListEnabled: Boolean,
    vm: TaskListsViewModel = hiltViewModel()
) {
    var itemsState = remember {
        vm.itemsCheckedMap
    }

    val incompleteList by vm.loadIncompleteTasks(listId)
        .collectAsStateWithLifecycle(initialValue = emptyList())
    val completeList by vm.loadCompleteTasks(listId)
        .collectAsStateWithLifecycle(initialValue = emptyList())

    TaskList(
        navController = navController,
        list = incompleteList,
        isEnabled = isIncompleteTaskListEnabled,
        itemsState = itemsState,
        onItemChecked = { task ->
            vm.updateTask(task)
        }
    ) {
        BigMessage(message = "No Tasks!")
    }

    TaskList(
        navController = navController,
        list = completeList,
        isEnabled = isCompletedTaskListEnabled,
        itemsState = itemsState,
        onItemChecked = {task ->
            vm.updateTask(task)
        }
    ) {
        when{
            completeList.count() == 0 && incompleteList.count() > 0 -> BigMessage(message = "You have ${incompleteList.count()} tasks to complete")
            completeList.count() == 0 && incompleteList.count() == 0 -> BigMessage(message = "There are no tasks to complete. Add tasks!")
        }
    }
}