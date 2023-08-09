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
import com.preciado.todo.core.models.TODOListTask
import com.preciado.todo.features.home.core.ListTaskItemViewModel

@Composable
fun ListTaskItemView(
    navController: NavController,
    todoListTask: TODOListTask,
    vm: ListTaskItemViewModel = hiltViewModel()
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate("tasks_details/${todoListTask.todoList_id}/${todoListTask.id}")
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = false,
                onCheckedChange = {
                    vm.onCheckChanged(todoListTask)
                }
            )

            Text(text = todoListTask.taskName)
        }
    }
}

@Preview
@Composable
fun PreviewListTaskItem() {
    ListTaskItemView(
        navController = rememberNavController(),
        todoListTask = TODOListTask(1, 1, "Some Task", "Doing some task!")
    )
}