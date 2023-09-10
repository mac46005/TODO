package com.preciado.todo.features.todo_tasks.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.BoxScopeInstance.align
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.preciado.todo.core.composables.composable_templates.components.ListItemTemplate
import com.preciado.todo.core.models.app_models.Task
import java.time.LocalDateTime
import com.preciado.todo.ui.theme.lightGreen
import com.preciado.todo.ui.theme.darkGreen










@Composable
fun TaskItem(
    onClick: () -> Unit,
    task: Task,
    onChecked: (Boolean)->Unit
){
    val isDarkMode = isSystemInDarkTheme()
    val green = if(isDarkMode) darkGreen else lightGreen

    var checkedState = remember {
        mutableStateOf(task.isCompleted)
    }
    val colorState by animateColorAsState(targetValue = if(checkedState.value == true) green  else Color.Transparent)





    ListItemTemplate(
        background = colorState,
        onClick = onClick
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
            if(task.isCompleted){
                Text(
                    modifier = Modifier.align(Alignment.BottomEnd).padding(bottom = 5.dp, end = 5.dp),
                    text = "completed: ${task.completedOn!!.toLocalDate()}",
                    color = Color.Gray,
                    fontSize = 10.sp
                )
            }

        Divider(
            modifier = Modifier
                .align(Alignment.BottomCenter)
        )
    }
}











@Preview
@Composable
fun PreviewTaskItem(){
    TaskItem(onClick = {},task = Task(taskName = "Task", isCompleted = true, createdOn = LocalDateTime.now())){

    }
}