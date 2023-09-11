package com.preciado.todo.core.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import com.preciado.todo.data.CRUD_Operation

sealed class Screen(
    private val baseRoute: String,
    val arguments: List<Argument>? = null
){


    // ======================= ROUTES ============================================
    object TaskSetList: Screen("todo_lists")

    object TaskList: Screen(
        "todo_tasks",
        listOf(
            Argument.ListId.buildNavArgument {
                type = NavType.IntType
                defaultValue = 0
            }
        )
    )

    object AddEditTaskSet: Screen(
        "add_edit_list",
        listOf(
            Argument.CrudOperation.buildNavArgument {
                type = NavType.IntType
                defaultValue = CRUD_Operation.CREATE.ordinal
            },
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
                defaultValue = CRUD_Operation.CREATE.ordinal
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

    object TaskDetails: Screen(
        "task_details",
        listOf(
            Argument.ListId.buildNavArgument {
                type = NavType.IntType
                defaultValue = 0
            },
            Argument.ID.buildNavArgument {
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

    fun withArgs(vararg args: String): String{
        return buildString{
            append(baseRoute)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}