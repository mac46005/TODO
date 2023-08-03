package com.preciado.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.preciado.todo.data.CRUDEnum
import com.preciado.todo.features.add_edit_list.AddEditListView
import com.preciado.todo.features.add_edit_list.core.AddEditListViewModel
import com.preciado.todo.features.add_edit_task.AddEditTaskView
import com.preciado.todo.features.home.HomeView
import com.preciado.todo.features.home.core.HomeViewModel
import com.preciado.todo.ui.theme.TODOTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TODOTheme() {
                Surface() {
                    var navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "home") {


                        composable("home") {
                            val vm by viewModels<HomeViewModel>()
                            HomeView(
                                vm = vm,
                                navController = navController
                            )
                        }




                        composable(
                            "add_edit_list/crud_operation={crud_operation}%todo_list_id={id}",
                            arguments = listOf(
                                navArgument("crud_operation") {
                                    defaultValue = CRUDEnum.CREATE.ordinal
                                },
                                navArgument("id") {
                                    defaultValue = 0
                                }
                            )
                        ) { backStackEntry ->
                            AddEditListView(
                                navController = navController,
                                crudOperation = CRUDEnum.fromInt(backStackEntry.arguments!!.getInt("crud_operation")),
                                tableId = backStackEntry.arguments!!.getInt("todo_list_id"),
                            )
                        }


                        composable(
                            "add_edit_list_task/crud_operation={crud_operation}&todo_list_id={todo_list_id}&todo_list_task_id={todo_list_task_id}",
                            arguments = listOf(
                                navArgument("crud_operation") {
                                    defaultValue = CRUDEnum.CREATE.ordinal
                                },
                                navArgument("todo_list_id") {
                                    type = NavType.IntType
                                },
                                navArgument("todo_list_task_id") {
                                    type = NavType.IntType
                                    defaultValue = 0
                                }
                            )
                        ) { backStack ->
                            AddEditTaskView(
                                navController = navController,
                                crudOperaton = CRUDEnum.fromInt(backStack.arguments!!.getInt("crud_operation")),
                                taskId = backStack.arguments!!.getInt("task_id")
                            )
                        }




                        composable(
                            "task_details/task_id={task_id}",
                            listOf(
                                navArgument("task_id") {
                                    type = NavType.IntType
                                }
                            )
                        ) { backStack ->

                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TODOTheme {
        Greeting("Android")
    }
}