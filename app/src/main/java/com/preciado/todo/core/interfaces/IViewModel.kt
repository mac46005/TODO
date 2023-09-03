package com.preciado.todo.core.interfaces

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController

interface IViewModel<T> {
    var header: String
    var subHeaders: List<String>
    var model: LiveData<T>
    fun onLoad(vararg args: Any)
    fun onBackButtonClicked()
    fun onNavigateTo()
    fun setNavigator(navController: NavController)
}