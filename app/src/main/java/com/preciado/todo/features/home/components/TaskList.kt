package com.preciado.todo.features.home.components

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.preciado.todo.core.models.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

private const val TAG = "TaskList"

@Composable
fun TaskList(
    navController: NavController,
    taskList: List<Task>
){
    //Log.i(TAG, "TaskList: taskList.count(${taskList.count()})")
    LazyColumn(
        contentPadding = PaddingValues(5.dp)
    ){
        items(taskList){ task ->
            //Log.i(TAG, "TaskList: LazyColumn: LazyListScope: items: task: ${task.taskName}")
            TaskItem(navController = navController, task = task)
        }
    }
}