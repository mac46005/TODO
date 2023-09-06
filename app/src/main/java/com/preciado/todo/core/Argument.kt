package com.preciado.todo.core

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavArgumentBuilder
import androidx.navigation.navArgument


sealed class Argument(val name: String){

    var navArgument: NamedNavArgument? = null

    object ID: Argument("id"){
        override fun buildNavArgument(navArgumentBuilder: NavArgumentBuilder.() -> Unit): Argument {
            navArgument = navArgument(name, navArgumentBuilder)
            return this
        }
    }

    object ListId: Argument("list_id"){
        override fun buildNavArgument(navArgumentBuilder: NavArgumentBuilder.() -> Unit): Argument {
            navArgument = navArgument(name, navArgumentBuilder)
            return this
        }

    }

    abstract fun buildNavArgument(navArgumentBuilder: NavArgumentBuilder.() -> Unit): Argument
}
