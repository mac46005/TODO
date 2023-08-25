package com.preciado.todo.features.home

import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.preciado.todo.core.views.BaseView
import com.preciado.todo.data.CRUDEnum
import com.preciado.todo.features.home.components.BigMessage
import com.preciado.todo.features.home.components.BottomBar
import com.preciado.todo.features.home.components.TODOListView
import com.preciado.todo.features.home.components.TaskListsView
import com.preciado.todo.features.home.core.HomeViewModel
import com.preciado.todo.ui.theme.TODOTheme

private const val TAG = "HomeView"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(
    vm: HomeViewModel = hiltViewModel(),
    navController: NavController
) {





    TODOTheme {
        val listId by vm.selectedTODOListId.observeAsState()

        Scaffold(
            bottomBar = {
                BottomBar(listId = listId!!) {
                    navController.navigate("add_edit_list/${CRUDEnum.CREATE.ordinal}/0")
                }
            }
        ) {
            val padding = it

            BaseView {

                val isIncompleteTasksEnabled by vm.incompleteTasksEnabled.observeAsState()
                val isCompletedTasksEnabled by vm.completedTasksEnabled.observeAsState()


                TODOListView(navController = navController) { listId ->
                    vm.resetListView()
                    vm._selectedTODOListId.value = listId
                }
                Divider()



                if (listId == 0) {
                    BigMessage(message = "No TODO List Selected")
                } else {
                    TaskListsView(
                        navController = navController,
                        listId = listId!!,
                        isIncompleteTaskListEnabled = isIncompleteTasksEnabled!!,
                        isCompletedTaskListEnabled = isCompletedTasksEnabled!!
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewHomeView() {
}