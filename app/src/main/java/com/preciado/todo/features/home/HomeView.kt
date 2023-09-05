package com.preciado.todo.features.home

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.preciado.todo.features.home.core.HomeViewModel

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