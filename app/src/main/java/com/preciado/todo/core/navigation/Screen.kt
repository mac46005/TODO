package com.preciado.todo.core.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import com.preciado.todo.core.Argument
import com.preciado.todo.data.CRUDEnum

sealed class Screen(
    private val baseRoute: String,
    val arguments: List<Argument>? = null
){


    // ======================= ROUTES ============================================
    object TODOLists: Screen("todo_lists")

    object TODOTasks: Screen(
        "todo_tasks",
        listOf(
            Argument.ListId.buildNavArgument {
                type = NavType.IntType
                defaultValue = 0
            }
        )
    )

    object AddEditList: Screen(
        "add_edit_list",
        listOf(
            Argument.ID.buildNavArgument {
                type = NavType.IntType
                defaultValue = 0
            }
        )
    )

    object AddEditTask: Screen(
        "add_edit_task",
        listOf(
            Argument.CrudOperation.buildNavArgument {
                type = NavType.IntType
                defaultValue = CRUDEnum.CREATE.ordinal
            },
            Argument.ID.buildNavArgument {
                type = NavType.IntType
                defaultValue = 0
            },
            Argument.ListId.buildNavArgument {
                type = NavType.IntType
                defaultValue = 0
            }
        )
    )
    // ======================== END ROUTES ==========================================














    fun fullRoute(): String{
        return buildString {
            append(baseRoute)
            arguments?.forEach { arg ->
                append("/{${arg.name}}")
            }
        }
    }

    fun namedNavArguments() : List<NamedNavArgument>{
        val argsList = mutableListOf<NamedNavArgument>()
        arguments!!.forEach { arg ->
            argsList.add(arg.navArgument!!)
        }
        return argsList
    }

    fun supplyArgs(vararg args: String): String{
        return buildString{
            append(baseRoute)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}