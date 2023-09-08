package com.preciado.todo.core.models.vm_models.models

import androidx.lifecycle.LiveData
import com.preciado.todo.core.models.vm_models.interfaces.IListVM
import kotlinx.coroutines.flow.Flow

abstract class ListVM<T>: VM<T>(), IListVM<T> {

    private var _listInfo: MutableMap<String, Any> = mutableMapOf<String, Any>()
    override var listInfo: MutableMap<String, Any>
        get() = _listInfo
        set(value) {
            _listInfo = value
        }
    private var _selectedItem: LiveData<T>? = null
    override var selectedItem: LiveData<T>?
        get() = _selectedItem
        set(value) {
            _selectedItem = value
        }
}