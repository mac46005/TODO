package com.preciado.todo.features.home

import androidx.compose.animation.AnimatedVisibility
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
import com.preciado.todo.features.home.components.ListButton
import com.preciado.todo.features.home.components.BigMessage
import com.preciado.todo.features.home.core.HomeViewModel
import com.preciado.todo.ui.theme.TODOTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(
    vm: HomeViewModel = hiltViewModel(),
    navController: NavController
) {

    val listState by vm.loadTodoLists().collectAsState(emptyList())
    val listId by vm.selectedTODOListId.observeAsState(0)
    val isListSelected by vm.isListSelected.observeAsState()
    val uncompletedTasks by vm.uncompletedTasks().collectAsState(initial = emptyList())

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
                                vm.onListSelected(todoListId = todoList.id)
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

                AnimatedVisibility(visible = isListSelected!!) {
                    if(uncompletedTasks.isEmpty()){
                        BigMessage(message = "No tasks!", otherMessage = { Text(text = "To add a new task push the \"+\" on the bottom right hand side.")})
                    }
                    LazyColumn{
                        items(uncompletedTasks){
                            Text(text = it.taskName)
                        }
                    }
                }
                AnimatedVisibility(visible = !isListSelected!!) {
                    BigMessage("Select a list!")
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewHomeView() {
}