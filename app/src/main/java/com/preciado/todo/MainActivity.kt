package com.preciado.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.preciado.todo.data.CRUDEnum
import com.preciado.todo.features.add_edit_list.AddEditListView
import com.preciado.todo.features.add_edit_task.AddEditTaskView
import com.preciado.todo.features.home.HomeView
import com.preciado.todo.features.todo_lists.TodoLists
import com.preciado.todo.features.todo_tasks.TodoTasks
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var navController = rememberNavController()

            NavHost(navController = navController, startDestination = "todo_lists") {

                composable("todo_lists") {
                    TodoLists(navController = navController)
                }

                composable("todo_tasks/{list_id}/{list_name}",
                    arguments = listOf(
                        navArgument("list_id") {
                            type = NavType.IntType
                            defaultValue = 0
                        },
                        navArgument("list_name") {
                            type = NavType.StringType
                            defaultValue = ""
                        }
                    )) { backStackEntry ->
                    TodoTasks(
                        navController = navController,
                        listId = backStackEntry.arguments!!.getInt("list_id"),
                        listName = backStackEntry.arguments!!.getString("list_name")!!
                    )

                }


                composable(
                    "add_edit_list/{crud_operation}/{list_id}",
                    arguments = listOf(
                        navArgument("crud_operation") {
                            defaultValue = CRUDEnum.CREATE.ordinal
                        },
                        navArgument("list_id") {
                            defaultValue = 0
                        }
                    )
                ) { backStackEntry ->
                    AddEditListView(
                        navController = navController,
                        crudOperation = CRUDEnum.fromInt(backStackEntry.arguments!!.getInt("crud_operation")),
                        tableId = backStackEntry.arguments!!.getInt("list_id"),
                    )
                }


                composable(
                    "add_edit_task/{crud_operation}/{list_id}/{list_name}/{task_id}",
                    arguments = listOf(
                        navArgument("crud_operation") {
                            defaultValue = CRUDEnum.CREATE.ordinal
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
                        crudEnum = CRUDEnum.fromInt(backStack.arguments!!.getInt("crud_operation")),
                        listId = backStack.arguments!!.getInt("list_id"),
                        listName = backStack.arguments!!.getString("list_name")!!,
                        taskId = backStack.arguments!!.getInt("task_id")
                    )
                }




                composable(
                    "task_details/{list_id}/{task_id}",
                    listOf(
                        navArgument("list_id") {
                            type = NavType.IntType
                        },
                        navArgument("task_id") {
                            type = NavType.IntType
                        }
                    )
                ) { backStack ->

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
