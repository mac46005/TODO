package com.preciado.todo.core.models.vm_models.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.preciado.todo.core.models.vm_models.interfaces.IVM

abstract class VM<T> () : ViewModel() , IVM<T> {

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

    protected var _model: MutableLiveData<T> = MutableLiveData()
    override var model: LiveData<T>? = _model

    abstract override fun setModel(model: T)
    abstract override fun onLoad(vararg args: Any)

    abstract override fun onBackButtonClicked()

    abstract override fun navigateTo(route: String)

    abstract override fun setNavigator(navController: NavController)

}