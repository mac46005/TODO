package com.preciado.todo.core.ui_models.interfaces

import androidx.lifecycle.LiveData
import androidx.navigation.NavController

interface IVM<T> {
    var title: String
    var headers: List<String>
    var model: LiveData<T>
    var navController: NavController
    fun onLoad(vararg args: Any)
    fun onBackButtonClicked()
    fun onNavigateTo(vararg args: Any)
    fun setNavigator(navController: NavController)
}