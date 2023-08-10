package com.preciado.todo.features.home.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.preciado.todo.core.models.Task
import kotlinx.coroutines.flow.Flow

@Composable
fun TasksList(
    modifier: Modifier = Modifier,
    navController: NavController,
    paddingValues: PaddingValues = PaddingValues(5.dp),
    tasks: List<Task>
    ){


    LazyColumn(
        modifier = modifier,
        contentPadding = paddingValues
    ){
        items(tasks){task ->
            TaskItem(navController = navController, task = task)
        }
    }
}