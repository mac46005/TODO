package com.preciado.todo.features.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.preciado.todo.core.views.BaseView
import com.preciado.todo.data.CRUDEnum
import com.preciado.todo.features.home.components.ListButton
import com.preciado.todo.features.home.core.HomeViewModel
import com.preciado.todo.ui.theme.TODOTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(
    modifier: Modifier = Modifier,
    vm: HomeViewModel? = null,
    navController: NavController
) {
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
                    for (i in 1..3) {
                        item {
                            ListButton(onClick = { /*TODO*/ }, text = "Text $i")
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
    HomeView(navController = rememberNavController())
}