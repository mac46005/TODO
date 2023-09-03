package com.preciado.todo.features.todo_tasks.components

import android.transition.Transition
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.KeyframesSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.preciado.todo.core.common_visuals.components.ListItem
import com.preciado.todo.core.models.Task
import java.time.LocalDateTime
import com.preciado.todo.ui.theme.lightGreen
import com.preciado.todo.ui.theme.darkGreen

@Composable
fun TaskItem(
    navController: NavController,
    task: Task,
    onChecked: (Boolean)->Unit
){
    val isDarkMode = isSystemInDarkTheme()
    val green = if(isDarkMode) darkGreen else lightGreen

    val animatable = remember {
        Animatable(initialValue = 0f)
    }

    LaunchedEffect(key1 = animatable){
        animatable.animateTo(
            animationSpec =
        )
    }

    var checkedState = remember {
        mutableStateOf(task.isCompleted)
    }
    val colorState by animateColorAsState(targetValue = if(checkedState.value == true) green  else Color.Transparent)
    ListItem(
        modifier = Modifier
            .alpha(),
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
                text = "created: ${task.createdOn.toLocalDate()}",
                color = Color.Gray,
                fontSize = 10.sp
            )
            if(task.isCompleted == true){
                Text(
                    text = "completed: ${task.completedOn!!.toLocalDate()}",
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