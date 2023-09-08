package com.preciado.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.preciado.todo.core.navigation.Argument
import com.preciado.todo.core.models.app_models.TODOList
import com.preciado.todo.core.navigation.Screen
import com.preciado.todo.data.CRUD_Operation
import com.preciado.todo.features.add_edit_list.AddEditListFormView
import com.preciado.todo.features.add_edit_task.AddEditTaskView
import com.preciado.todo.features.home.HomeView
import com.preciado.todo.features.task_details.TaskDetails
import com.preciado.todo.features.todo_lists.TodoListsView
import com.preciado.todo.features.todo_tasks.TodoTasksView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var navController = rememberNavController()

            NavHost(navController = navController, startDestination = "todo_lists") {

                composable(Screen.TODOLists.fullRoute()) {
                    TodoListsView(navController = navController)
                }

                composable(Screen.TODOTasks.fullRoute(),
                    arguments = Screen.TODOTasks.namedNavArguments()
                    ) { backStackEntry ->
                    TodoTasksView(
                        navController = navController,
                        listId = backStackEntry.arguments!!.getInt(Argument.ListId.name)
                    )
                }


                composable(
                    Screen.AddEditList.fullRoute(),
                    Screen.AddEditList.namedNavArguments()
                ) { backStackEntry ->
                    AddEditListFormView(
                        navController = navController,
                        crudOperation = CRUD_Operation
                            .fromInt(backStackEntry.arguments!!.getInt(Argument.CrudOperation.name)),
                        todoList = TODOList(id = backStackEntry.arguments!!.getInt(Argument.ID.name))
                    )
                }


                composable(
                    "add_edit_task/{crud_operation}/{list_id}/{list_name}/{task_id}",
                    arguments = listOf(
                        navArgument("crud_operation") {
                            defaultValue = CRUD_Operation.CREATE.ordinal
                        },
                        navArgument("list_id") {
                            type = NavType.IntType
                        },
                        navArgument("list_name") {
                            type = NavType.StringType
                            defaultValue = ""
                        },
                        navArgument("task_id") {
                            type = NavType.IntType
                            defaultValue = 0
                        }
                    )
                ) { backStack ->
                    AddEditTaskView(
                        navController = navController,
                        crudOperation = CRUD_Operation.fromInt(backStack.arguments!!.getInt("crud_operation")),
                        listId = backStack.arguments!!.getInt("list_id"),
                        listName = backStack.arguments!!.getString("list_name")!!,
                        taskId = backStack.arguments!!.getInt("task_id")
                    )
                }




                composable(
                    Screen.TaskDetails.fullRoute(),
                    Screen.TaskDetails.namedNavArguments()
                ) { backStack ->
                    TaskDetails(
                        navController = navController,
                        taskId = backStack.arguments!!.getInt(Argument.ListId.name),
                        listId = backStack.arguments!!.getInt(Argument.ID.name)
                    )
                }






                composable("home") {
                    HomeView(
                        navController = navController
                    )
                }


            }
        }
    }
}
