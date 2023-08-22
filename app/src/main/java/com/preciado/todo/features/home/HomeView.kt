package com.preciado.todo.features.home

import android.util.Log
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
import androidx.navigation.NavController
import com.preciado.todo.R
import com.preciado.todo.core.common_visuals.components.TransparentButton
import com.preciado.todo.core.views.BaseView
import com.preciado.todo.data.CRUDEnum
import com.preciado.todo.features.home.components.ListButton
import com.preciado.todo.features.home.components.BigMessage
import com.preciado.todo.features.home.components.TasksLists
import com.preciado.todo.features.home.core.HomeViewModel
import com.preciado.todo.ui.theme.TODOTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(
    vm: HomeViewModel = hiltViewModel(),
    navController: NavController
) {

    val listId by vm.selectedTODOListId.observeAsState(0)

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
                                Icon(painter = painterResource(id = R.drawable.red_x_light), contentDescription = "Uncompleted List")
                            }
                            Button(onClick = { /*TODO*/ }) {
                                Icon(painter = painterResource(id = R.drawable.green_check_light), contentDescription = "Completed List")
                            }
                            Button(onClick = { /*TODO*/ }) {
                                Icon(painter = painterResource(id = R.drawable.outline_delete_24)   , contentDescription = "Delete List")
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
                                Log.d("HomeView", "selected list id: ${listId}")
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


                if(listId == 0){
                    BigMessage(
                        message = "Please select a TODO list from above!",
                        paddingBottom = padding.calculateBottomPadding()
                    )
                }else{
                    TasksLists(navController = navController,listId = listId)
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewHomeView() {
}