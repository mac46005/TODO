package com.preciado.todo.features.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.preciado.todo.R
import com.preciado.todo.core.common_visuals.components.TransparentButton
import com.preciado.todo.core.views.BaseView
import com.preciado.todo.data.CRUDEnum
import com.preciado.todo.features.home.components.CompletedTasksTab
import com.preciado.todo.features.home.components.ListButton
import com.preciado.todo.features.home.core.HomeViewModel
import com.preciado.todo.ui.theme.TODOTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(
    vm: HomeViewModel = hiltViewModel(),
    navController: NavController
) {


    LaunchedEffect(key1 = true) {
        vm.initialize()
    }

//    val listState by vm.todoLists.observeAsState()
//    val listTasksState by vm.todoList.observeAsState()
    val listState by vm.loadTodoLists().collectAsState(emptyList())
    val listId by vm.selectedTODOListId.observeAsState(0)
    val isListSelected by vm.isListSelected.observeAsState()
    val listTasksState by vm.loadTodoListTasks(listId).collectAsState(emptyList())


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
                            Button(onClick = { /*TODO*/ }) {

                            }
                            Button(onClick = { /*TODO*/ }) {

                            }
                            Button(onClick = { /*TODO*/ }) {

                            }
                        }





                        //Add new task
                        Button(
                            onClick = {
                                navController.navigate("add_edit_list_task/${CRUDEnum.CREATE.ordinal}/${listId}/0")
                            },
                            enabled = isListSelected!!
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



                //TODOLists
                LazyRow() {
                    items(listState!!) { todoList ->
                        TransparentButton(
                            onClick =
                            {
                                vm.loadTodoListTasks(todoListId = todoList.id)
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

                // UNCOMPLETED TASKS
                LazyColumn() {
                    items(listTasksState!!) { task ->
                        //TODO Create view for individual task item
                        Text(text = task.taskName)
                    }
                }


                // COMPLETED TASKS
                // TODO Create a view that shows the number of completed tasks and and expand button to view the list
                CompletedTasksTab {

                }
                // TODO Create the LazyColumn that shows the completed lists but it is hidden until the user clicks the button
            }
        }
    }
}

@Preview
@Composable
fun PreviewHomeView() {
}