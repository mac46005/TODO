package com.preciado.todo.features.home.components

import android.util.Log
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

private const val TAG = "TaskItem"

@Composable
fun TaskItem(
    navController: NavController,
    task: Task,
    onCheckChanged: ((Boolean) -> Unit)
){



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
                    checked = task.isCompleted,
                    onCheckedChange =  onCheckChanged
                )

                Text(text = task.taskName)
            }

            Divider()
        }


    }
}


