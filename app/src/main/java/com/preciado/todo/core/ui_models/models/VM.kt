package com.preciado.todo.core.ui_models.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.preciado.todo.core.ui_models.interfaces.IVM

abstract class VM<T> () : ViewModel() ,IVM<T> {

    private var _navController: NavController? = null
    override var navController: NavController?
        get(){
            return _navController
        }
        set(value) {
            _navController = value
        }

    private var _title: String = ""
    override var title: String
        get() = _title
        set(value) {
            _title = value
        }

    private var _headers: List<String> = emptyList()
    override var headers: List<String>
        get() = _headers
        set(value) {
            _headers = value
        }

    private var _model: LiveData<T>? = null
    override var model: LiveData<T>?
        get() = _model
        set(value) {
            _model = value
        }


    abstract override fun onLoad(vararg args: Any)

    abstract override fun onBackButtonClicked()

    abstract override fun onNavigateTo(vararg args: Any)

    abstract override fun setNavigator(navController: NavController)

}