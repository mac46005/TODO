package com.preciado.todo.core.models.vm_models.interfaces

import androidx.lifecycle.LiveData
import androidx.navigation.NavController

interface IVM<T> {
    var title: String
    var headers: List<String>
    var model: LiveData<T>?
    var navController: NavController?

    fun setModel(model: T)
    fun onLoad(vararg args: Any)
    fun onBackButtonClicked()
    fun navigateTo(route: String)
}