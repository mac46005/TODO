package com.preciado.todo.features.taskset_list

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.preciado.todo.core.composables.composables_todo.components.EmptyListMessage
import com.preciado.todo.core.composables.composables_todo.views.TODOListView
import com.preciado.todo.core.models.app_models.models.TaskSet
import com.preciado.todo.core.navigation.Screen
import com.preciado.todo.data.CRUD_Operation
import com.preciado.todo.features.taskset_list.components.TaskSetItem
import com.preciado.todo.features.taskset_list.core.TaskSetListVM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskSetListView(
    navController: NavController,
    vm: TaskSetListVM? = hiltViewModel()
){

    vm!!.onLoad(navController)

    val list by vm.loadList().collectAsStateWithLifecycle(initialValue = emptyList())

    TODOListView<TaskSet>(
        title = "Your Lists",
        backButtonVisible = false,
        onFloatingActionButtonClicked = {
            vm.navigateTo(Screen.AddEditTaskSet.withArgs(CRUD_Operation.CREATE.ordinal.toString(), "0"))
        },
        list = list?: emptyList(),
        emptyListMessage = {
            EmptyListMessage()
        }
    ) {item ->
        TaskSetItem(name = item.name) {
            vm.onItemSelected(item)
        }

    }
}

@Preview
@Composable
fun PreviewTodoLists(){
    TaskSetListView(
        navController = rememberNavController(),
        vm = null
    )
}