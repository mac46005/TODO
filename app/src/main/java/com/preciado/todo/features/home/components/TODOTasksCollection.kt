package com.preciado.todo.features.home.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.preciado.todo.features.home.core.TODOTasksCollectionViewModel

@Composable
fun TODOTasksCollection(
    listId: Int,
    vm: TODOTasksCollectionViewModel = hiltViewModel()
){
    val _listIdState by vm.listId.observeAsState(initial = 0)
    LaunchedEffect(key1 = true){
        vm.setListId(listId)
    }


}