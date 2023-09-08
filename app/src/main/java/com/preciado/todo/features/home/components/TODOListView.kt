package com.preciado.todo.features.home.components

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.preciado.todo.core.composables.composable_templates.components.TransparentButton
import com.preciado.todo.data.CRUD_Operation
import com.preciado.todo.features.home.core.TODOListViewModel
import com.preciado.todo.ui.theme.TODOTheme

private const val TAG = "TODOListView"

@Composable
fun TODOListView(
    navController: NavController,
    vm: TODOListViewModel = hiltViewModel(),
    onListItemClicked: (Int) -> Unit,
){
    val list by vm.loadTodoLists().collectAsState(initial = emptyList())
    TODOTheme() {
        LazyRow(){
            items(list!!){ item ->
                TransparentButton(
                    onClick = {
                        onListItemClicked(item.id)
                    }
                ) {
                    Text(text = item.name)
                }
            }

            item{
                ListButton(
                    onClick = {
                        navController.navigate("add_edit_list/${CRUD_Operation.CREATE.ordinal}/0")
                    },
                    text = "+ New List"
                )
            }
        }
    }

}