package com.preciado.todo.features.todo_tasks.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.preciado.todo.core.common_visuals.components.ListItem
import com.preciado.todo.core.models.Task
import java.time.LocalDateTime

@Composable
fun TaskItem(
    navController: NavController,
    task: Task,
    onChecked: (Boolean)->Unit
){
    var checkedState = remember {
        mutableStateOf(task.isCompleted)
    }
    val colorState by animateColorAsState(targetValue = if(checkedState.value == true) Color.Green else Color.Transparent)

    ListItem(
        background = colorState,
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
                    onChecked(checked)
                }
            )
            Text(text = task.taskName)

        }
        Row(
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            Text(
                text = "created: ${task.createdOn.toLocalDate().toString()}",
                color = Color.Gray,
                fontSize = 10.sp
            )
            if(task.isCompleted == true){
                Text(
                    text = "completed: ",
                    color = Color.Gray,
                    fontSize = 10.sp
                )
            }

        }
    }
}

@Preview
@Composable
fun PreviewTaskItem(){
    TaskItem(navController = rememberNavController(),task = Task(taskName = "Task", isCompleted = true, createdOn = LocalDateTime.now())){

    }
}