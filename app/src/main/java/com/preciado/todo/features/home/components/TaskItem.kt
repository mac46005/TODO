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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.preciado.todo.core.models.app_models.Task

private const val TAG = "TaskItem"

@Composable
fun TaskItem(
    navController: NavController,
    task: Task,
    onChecked: (Boolean) -> Unit
){



    Box(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            navController.navigate("")
        }
    ){
        var checked = remember {
            mutableStateOf(task.isCompleted)
        }

        Column {
            Divider()

            Row() {


                Checkbox(
                    checked = checked.value,
                    onCheckedChange = { _checked ->
                        checked.value = _checked
                        onChecked(_checked)
                    }
                )
                Text(text = task.taskName)
            }

            Divider()
        }


    }
}


