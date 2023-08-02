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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.preciado.todo.core.models.TODOListTask

@Composable
fun ListTaskItem(
    modifier: Modifier = Modifier,
    navController: NavController,
    todoListTask: TODOListTask
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate("details")
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = true,
                onCheckedChange = {

                }
            )

            Text(text = todoListTask.taskName)
        }
    }
}

@Preview
@Composable
fun PreviewListTaskItem() {
    ListTaskItem(
        navController = rememberNavController(),
        todoListTask = TODOListTask(1, 1, "Some Task", "Doing some task!")
    )
}