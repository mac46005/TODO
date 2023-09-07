package com.preciado.todo.core.models.vm_models.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.preciado.todo.core.models.vm_models.interfaces.IFormVM
import com.preciado.todo.data.CRUDEnum
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
abstract class FormVM<T>: IFormVM<T>, ViewModel() {
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

    private  var _model: MutableLiveData<T> = MutableLiveData()
    override var model: LiveData<T>? = _model


    private var _crudEnum: CRUDEnum = CRUDEnum.CREATE
    override var crudEnum: CRUDEnum
        get() = _crudEnum
        set(value){
            _crudEnum = value
        }
}