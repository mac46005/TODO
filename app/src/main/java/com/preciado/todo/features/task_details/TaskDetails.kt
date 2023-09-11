package com.preciado.todo.features.task_details

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.preciado.todo.core.composables.composables_todo.components.Header
import com.preciado.todo.core.composables.composables_todo.views.TODOMainView
import com.preciado.todo.core.models.app_models.models.Task

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetails(
    navController: NavController,
    task: Task
){
    val scrollState = rememberScrollState()

    TODOMainView() {
        Column(
            modifier = Modifier.scrollable(scrollState, Orientation.Vertical)
        ) {
            Header(header = "ADD TITLE")
            Divider()
            //details
            Divider()
            //logger here
            LazyColumn(

            ){

            }
            Row() {
                TextField(value = "",
                    onValueChange = {

                    }
                )
                Button(
                    onClick = {

                    }
                ) {

                }
            }
        }
    }
}