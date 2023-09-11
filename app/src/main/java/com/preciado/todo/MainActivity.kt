package com.preciado.todo

import android.os.Bundle
import android.util.Log
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

private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var navController = rememberNavController()

            NavHost(navController = navController, startDestination = "todo_lists") {

                composable(Screen.TaskSetList.fullRoute()) {
                    TaskSetListView(navController = navController)
                }

                composable(Screen.TaskList.fullRoute(),
                    arguments = Screen.TaskList.namedNavArguments()
                    ) { backStackEntry ->
                    val taskSet = TaskSet(id = backStackEntry.arguments!!.getInt(Argument.ListId.name))
                    Log.i(TAG, "onCreate: Navigating to ${Screen.TaskList.fullRoute()} with args: $taskSet")
                    TaskListView(
                        navController = navController,
                        taskSet = taskSet
                    )
                }


                composable(
                    Screen.AddEditTaskSet.fullRoute(),
                    Screen.AddEditTaskSet.namedNavArguments()
                ) { backStackEntry ->

                    val taskSet = TaskSet(id = backStackEntry.arguments!!.getInt(Argument.ID.name))
                    Log.i(TAG, "onCreate: Navigating to ${Screen.AddEditTaskSet.fullRoute()} with args $taskSet")
                    AddEditTaskSetFormView(
                        navController = navController,
                        crudOperation = CRUD_Operation
                            .fromInt(backStackEntry.arguments!!.getInt(Argument.CrudOperation.name)),
                        taskSet = taskSet
                    )
                }


                //crudop/listid/id
                composable(
                    Screen.AddEditTask.fullRoute(),
                    Screen.AddEditTask.namedNavArguments()
                ) { backStack ->
                    val taskSet = TaskSet(backStack.arguments!!.getInt(Argument.ListId.name))
                    val task = Task(
                        id = backStack.arguments!!.getInt(Argument.ID.name),
                        taskSet = taskSet
                    )

                    Log.i(TAG, "onCreate: Navigating to ${Screen.AddEditTask.fullRoute()} with args $task")
                    AddEditTaskFormView(
                        navController = navController,
                        crudOperation = CRUD_Operation.fromInt(backStack.arguments!!.getInt(Argument.CrudOperation.name)),
                        task = task
                    )
                }




                composable(
                    Screen.TaskDetails.fullRoute(),
                    Screen.TaskDetails.namedNavArguments()
                ) { backStack ->
                    val taskSet = TaskSet(backStack.arguments!!.getInt(Argument.ListId.name))
                    val task = Task(
                        id = backStack.arguments!!.getInt(Argument.ID.name),
                        taskSet = taskSet
                    )
                    Log.i(TAG, "onCreate: Navigating to ${Screen.TaskDetails}: $task")
                    TaskDetails(
                        navController = navController,
                        task = task
                    )
                }



            }
        }
    }
}
