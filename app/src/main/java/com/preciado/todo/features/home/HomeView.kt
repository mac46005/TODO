package com.preciado.todo.features.home

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.preciado.todo.R
import com.preciado.todo.core.common_visuals.components.TransparentButton
import com.preciado.todo.core.views.BaseView
import com.preciado.todo.data.CRUDEnum
import com.preciado.todo.features.home.components.ListButton
import com.preciado.todo.features.home.core.HomeViewModel
import com.preciado.todo.ui.theme.TODOTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(
    modifier: Modifier = Modifier,
    vm: HomeViewModel,
    navController: NavController
) {



    LaunchedEffect(key1 = true){
        vm.initialize()
    }

    val listState by vm.todoLists.observeAsState()
    val listTasksState by vm.todoList.observeAsState()
    val listId by vm.selectedTODOListId.observeAsState()

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
                        Button(onClick = {
                            navController.navigate("add_edit_list_task/crud_operation=${CRUDEnum.CREATE}%todo_list_id=${listId}%todo_list_task_id=0")
                        }) {
                            Icon(painter = painterResource(id = R.drawable.baseline_add_24), contentDescription = "")
                        }
                    }
                }
            }
        ) {




            val padding = it
            BaseView {
                LazyRow() {
                    items(listState!!){ todoList ->
                        TransparentButton(
                            onClick =
                            {
                                vm.loadTodoList(todoListId = todoList.id)
                            }
                        ) {
                            Text(text = todoList.name)
                        }
                    }
                    item{
                        ListButton(onClick = {
                            navController.navigate("")
                        }, text = "+ New List")
                    }
                }
                Divider()

                LazyRow(){
                    items(listTasksState!!){

                    }

                }




            }
        }
    }
}

@Preview
@Composable
fun PreviewHomeView() {
    HomeView(
        navController = rememberNavController(),
        vm = viewModel()
    )
}