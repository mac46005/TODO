package com.preciado.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.preciado.todo.core.navigation.Argument
import com.preciado.todo.core.models.app_models.models.TaskSet
import com.preciado.todo.core.models.app_models.models.Task
import com.preciado.todo.core.navigation.Screen
import com.preciado.todo.data.CRUD_Operation
import com.preciado.todo.features.add_edit_taskset.AddEditTaskSetFormView
import com.preciado.todo.features.add_edit_task.AddEditTaskFormView
import com.preciado.todo.features.task_details.TaskDetails
import com.preciado.todo.features.taskset_list.TaskSetListView
import com.preciado.todo.features.task_list.TaskListView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var navController = rememberNavController()

            NavHost(navController = navController, startDestination = "todo_lists") {

                composable(Screen.TODOLists.fullRoute()) {
                    TaskSetListView(navController = navController)
                }

                composable(Screen.TODOTasks.fullRoute(),
                    arguments = Screen.TODOTasks.namedNavArguments()
                    ) { backStackEntry ->
                    TaskListView(
                        navController = navController,
                        listId = backStackEntry.arguments!!.getInt(Argument.ListId.name)
                    )
                }


                composable(
                    Screen.AddEditList.fullRoute(),
                    Screen.AddEditList.namedNavArguments()
                ) { backStackEntry ->
                    AddEditTaskSetFormView(
                        navController = navController,
                        crudOperation = CRUD_Operation
                            .fromInt(backStackEntry.arguments!!.getInt(Argument.CrudOperation.name)),
                        taskSet = TaskSet(id = backStackEntry.arguments!!.getInt(Argument.ID.name))
                    )
                }


                //crudop/listid/id
                composable(
                    Screen.AddEditTask.fullRoute(),
                    Screen.AddEditTask.namedNavArguments()
                ) { backStack ->
                    AddEditTaskFormView(
                        navController = navController,
                        crudOperation = CRUD_Operation.fromInt(backStack.arguments!!.getInt(Argument.CrudOperation.name)),
                        task = Task(
                            id = backStack.arguments!!.getInt(Argument.ID.name),
                            taskSetId = TaskSet(backStack.arguments!!.getInt(Argument.ListId.name))
                        )
                    )
                }




                composable(
                    Screen.TaskDetails.fullRoute(),
                    Screen.TaskDetails.namedNavArguments()
                ) { backStack ->
                    TaskDetails(
                        navController = navController,
                        task = Task(
                            id = backStack.arguments!!.getInt(Argument.ID.name),
                            taskSetId = TaskSet(backStack.arguments!!.getInt(Argument.ListId.name))
                        )
                    )
                }



            }
        }
    }
}
