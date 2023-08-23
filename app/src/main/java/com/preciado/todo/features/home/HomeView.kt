package com.preciado.todo.features.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.preciado.todo.R
import com.preciado.todo.core.common_visuals.components.TransparentButton
import com.preciado.todo.core.views.BaseView
import com.preciado.todo.data.CRUDEnum
import com.preciado.todo.features.home.components.ListButton
import com.preciado.todo.features.home.components.BigMessage
import com.preciado.todo.features.home.components.TaskList
import com.preciado.todo.features.home.core.HomeViewModel
import com.preciado.todo.ui.theme.TODOTheme

private const val TAG = "HomeView"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(
    vm: HomeViewModel = hiltViewModel(),
    navController: NavController
) {


    val listId by vm.selectedTODOListId.observeAsState(0)

    val incompleteTasksButtonEnabled by vm.incompTButtonEnabled.observeAsState()
    val completedTasksButtonEnabled by vm.compTButtonEnabled.observeAsState()

    val incompleteTasksEnabled by vm.incompleteTasksEnabled.observeAsState()
    val completedTasksEnabled by vm.completedTasksEnabled.observeAsState()

    val incompleteTasksCount by vm.incompleteTasksCount.observeAsState()
    val completedTasksCount by vm.completedTasksCount.observeAsState()

    TODOTheme {
        Scaffold(
            bottomBar = {
                BottomAppBar(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row() {


//                            UNCOMPLETED LIST VIEW BUTTON
                            Button(
                                onClick = {
                                    vm.setIncompleteTasksButtonEnabled(!incompleteTasksButtonEnabled!!)
                                    vm.setIncompleteTasksEnabled(!incompleteTasksEnabled!!)
                                    vm.setCompletedTasksButtonEnabled(!completedTasksButtonEnabled!!)
                                    vm.setCompleteTasksEnabled(!completedTasksEnabled!!)
                                },
                                enabled = incompleteTasksButtonEnabled!!
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.baseline_close_24),
                                    contentDescription = "Uncompleted List"
                                )
                            }
//                            COMPLETED LIST VIEW BUTTON
                            Button(
                                onClick = {
                                    vm.setIncompleteTasksButtonEnabled(!incompleteTasksButtonEnabled!!)
                                    vm.setIncompleteTasksEnabled(!incompleteTasksEnabled!!)
                                    vm.setCompletedTasksButtonEnabled(!completedTasksButtonEnabled!!)
                                    vm.setCompleteTasksEnabled(!completedTasksEnabled!!)
                                },
                                enabled = completedTasksButtonEnabled!!
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_check_24),
                                    contentDescription = "Completed List"
                                )
                            }
                            Button(
                                onClick = {

                                }
                            ) {

                            }
                        }


                        //Add new task
                        Button(
                            onClick = {
                                navController.navigate("add_edit_list_task/${CRUDEnum.CREATE.ordinal}/${listId}/0")
                            },
                            enabled = listId != 0
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_add_24),
                                contentDescription = "Add new task button"
                            )
                        }
                    }
                }
            }
        ) {
            val padding = it
            BaseView {

                val listState by vm.loadTodoLists().collectAsState(emptyList())

                //TODOLists
                LazyRow() {
                    items(listState!!) { todoList ->
                        TransparentButton(
                            onClick =
                            {
                                vm.onListSelected(todoListId = todoList.id)
                                vm.setIncompleteTasksButtonEnabled(false)
                                vm.setIncompleteTasksEnabled(true)
                                vm.setCompletedTasksButtonEnabled(true)
                                vm.setCompleteTasksEnabled(false)
                            }
                        ) {
                            Text(text = todoList.name)
                        }
                    }
                    item {
                        ListButton(onClick = {
                            navController.navigate("add_edit_list/${CRUDEnum.CREATE.ordinal}/0")
                        }, text = "+ New List")
                    }
                }

                Divider()

                if (listId == 0) {
                    BigMessage(message = "No TODO List Selected")
                } else {


                    val incompletedTasks by vm.uncompletedTasks(listId).collectAsStateWithLifecycle(
                        initialValue = emptyList()
                    ).also { state ->
                        vm.setIncompletedTasksCount(state.value.count())
                    }

                    val completedTasks by vm.completedTasks(listId).collectAsStateWithLifecycle(
                        initialValue = emptyList()
                    ).also { state ->
                        vm.setCompletedTasksCount(state.value.count())
                    }

                    AnimatedVisibility(visible = incompleteTasksEnabled!!) {
                        if(incompletedTasks.isNotEmpty()){
                            TaskList(navController = navController, taskList = incompletedTasks)
                        }else{
                            BigMessage(message = "No tasks!")
                        }
                    }

                    AnimatedVisibility(visible = completedTasksEnabled!!) {
                        if(completedTasks.isNotEmpty()){
                            TaskList(navController = navController, taskList = completedTasks)
                        }else{
                            when{
                                completedTasksCount!! == 0 &&  incompleteTasksCount!! > 0 -> BigMessage(message = "You have ${incompleteTasksCount} to complete")
                                completedTasksCount!! == 0 && incompleteTasksCount!! == 0 -> BigMessage(message = "No Tasks!")
                            }
                        }

                    }

                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewHomeView() {
}