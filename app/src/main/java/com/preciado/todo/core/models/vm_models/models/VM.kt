package com.preciado.todo.core.models.vm_models.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.preciado.todo.core.models.vm_models.interfaces.IVM

abstract class VM<T> : ViewModel() , IVM<T> {

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

    override fun setModel(model: T) {
        _model.value = model
    }

    override fun getModel(): T {
        return _model.value!!
    }

    override fun navigateTo(route: String) {
        _navController!!.navigate(route)
    }

}