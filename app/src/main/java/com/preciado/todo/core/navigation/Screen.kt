package com.preciado.todo.core.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import com.preciado.todo.core.Argument

sealed class Screen(
    private val baseRoute: String,
    private val arguments: Map<String, Argument>? = null
){

    object TODOLists: Screen("todo_lists")
    object TODOTasks: Screen(
        "todo_tasks",
        mapOf(
            Argument.ID.name to Argument.ID.buildNavArgument{
                type = NavType.IntType
                defaultValue = 0
            }
        )
    )

    fun fullRoute(): String{
        return buildString {
            append(baseRoute)
            arguments!!.forEach { (name, _) ->
                append("/{$name}")
            }
        }
    }

    fun namedNavArguments() : List<NamedNavArgument>{
        var argsList = mutableListOf<NamedNavArgument>()
        arguments!!.forEach { (_, argument) ->
            argsList.add(argument.navArgument!!)
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