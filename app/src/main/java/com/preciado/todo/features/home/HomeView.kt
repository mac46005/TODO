package com.preciado.todo.features.home

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
    Scaffold(
        bottomBar = {

        }
    ){
        paddingValues ->
        var pv = paddingValues
        

    }
}

@Preview
@Composable
fun PreviewHomeView() {
}