package com.preciado.todo.features.home

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.preciado.todo.core.common_visuals.components.TransparentButton
import com.preciado.todo.core.models.TODOList
import com.preciado.todo.core.views.BaseView
import com.preciado.todo.data.CRUDEnum
import com.preciado.todo.data.DatabaseHelper
import com.preciado.todo.data.TODOListTable
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
    val listState by vm.todoList.observeAsState()

    LaunchedEffect(key1 = true){
        vm.initialize()
    }
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
                        Button(onClick = { /*TODO*/ }) {
                            Text(text = "+")
                        }
                    }
                }
            }
        ) {
            val padding = it
            BaseView {
                LazyRow() {
//                    for (i in 1..3) {
//                        item {
//                            ListButton(onClick = { /*TODO*/ }, text = "Text $i")
//                        }
//                    }

                    items(listState!!){ todoList ->
                        TransparentButton(onClick = { /*TODO*/ }) {
                            Text(text = todoList.name)
                        }
                    }
                    item{
                        ListButton(onClick = {
                                             navController.navigate("add_edit_list/crud_operation=${CRUDEnum.CREATE.ordinal}%todo_list_id=0")
                        }, text = "+ New List")
                    }
                }
                Divider()

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