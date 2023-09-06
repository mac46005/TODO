package com.preciado.todo.core.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavArgumentBuilder
import androidx.navigation.navArgument

class ArgumentClass(val name: String){
    var navArg: NamedNavArgument? = null
    fun setNavArgument(navArgumentBuilder:  NavArgumentBuilder.() -> Unit): ArgumentClass{
        navArg = navArgument(name, navArgumentBuilder)
        return this
    }
}