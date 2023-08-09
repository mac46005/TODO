package com.preciado.todo.features.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.preciado.todo.core.models.Task
import com.preciado.todo.ui.theme.TODOTheme

@Composable
fun TasksView(
    navController: NavController,
    uncompletedTasks: List<Task> = emptyList(),
    completedTasks: List<Task> = emptyList()
){
    var isCompletedTasksVisible = remember {
        mutableStateOf(false)
    }


    TODOTheme() {
        Column(
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            //Uncompleted Tasks
            TasksList(
                modifier = Modifier.weight(1f),
                navController = navController,
                tasks = uncompletedTasks
            )


            if(completedTasks.isNotEmpty()){
                Column (
                    modifier = Modifier.weight(0f)
                        ){
                    CompletedTasksTab {

                    }
                    AnimatedVisibility(visible = isCompletedTasksVisible.value) {
                        
                    }

                }
            }
        }
    }

}

@Preview
@Composable
fun PreviewTasksView(){
    TasksView(navController = rememberNavController())
}