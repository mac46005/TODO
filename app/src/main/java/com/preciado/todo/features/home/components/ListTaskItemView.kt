package com.preciado.todo.features.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.preciado.todo.core.models.Task
import com.preciado.todo.features.home.core.ListTaskItemViewModel

@Composable
fun ListTaskItemView(
    navController: NavController,
    task: Task,
    vm: ListTaskItemViewModel = hiltViewModel()
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate("tasks_details/${task.todoList_id}/${task.id}")
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = false,
                onCheckedChange = {
                    vm.onCheckChanged(task)
                }
            )

            Text(text = task.taskName)
        }
    }
}

@Preview
@Composable
fun PreviewListTaskItem() {
    ListTaskItemView(
        navController = rememberNavController(),
        task = Task(1, 1, "Some Task", "Doing some task!")
    )
}