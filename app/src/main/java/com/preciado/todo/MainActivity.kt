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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.preciado.todo.data.CRUDEnum
import com.preciado.todo.features.add_edit_list.AddEditListView
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
                            "add_edit_list/crud_operation={crud_operation}",
                            arguments = listOf(
                                navArgument("crud_operation"){
                                    defaultValue = CRUDEnum.CREATE.ordinal
                                }
                            )
                        ){ backStackEntry ->
                            AddEditListView(
                                navController = navController,
                                crudOperation = CRUDEnum.fromInt(backStackEntry.arguments!!.getInt("crud_operation"))
                            )
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