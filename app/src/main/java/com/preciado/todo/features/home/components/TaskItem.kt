package com.preciado.todo.features.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.preciado.todo.core.models.Task
import com.preciado.todo.features.home.core.TaskItemViewModel

@Composable
fun TaskItem(
    navController: NavController,
    task: Task,
    vm: TaskItemViewModel = hiltViewModel()
){
    LaunchedEffect(key1 = true){
        vm.setTask(task)
    }

    val taskState by vm.task.observeAsState()

    var isClicked = remember {
        mutableStateOf(false)
    }

    Box(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            navController.navigate("")
        }
    ){
        Column {
            Divider()

            Row() {
                Checkbox(
                    checked = isClicked.value,
                    onCheckedChange = {
                        isClicked.value = isClicked.value == false
                        vm.changeCompletion(isClicked.value)
                    }
                )
                Text(text = taskState!!.taskName)
            }

            Divider()
        }


    }
}


