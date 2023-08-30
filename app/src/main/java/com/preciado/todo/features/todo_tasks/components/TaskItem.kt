package com.preciado.todo.features.todo_tasks.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.preciado.todo.core.common_visuals.components.ListItem
import com.preciado.todo.core.models.Task

@Composable
fun TaskItem(
    navController: NavController,
    task: Task
){
    var checkedState = remember {
        mutableStateOf(task.isCompleted)
    }
    ListItem(
        onClick = {
            navController.navigate("task_details/${task.todoList_id}/${task.id}")
        }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = checkedState.value,
                onCheckedChange = { checked ->
                    checkedState.value = checked
                }
            )
            Text(text = task.taskName)
        }
    }
}

@Preview
@Composable
fun PreviewTaskItem(){
    TaskItem(navController = rememberNavController(),task = Task(taskName = "Task", isCompleted = true))
}