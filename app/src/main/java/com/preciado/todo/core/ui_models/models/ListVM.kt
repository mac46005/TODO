package com.preciado.todo.core.ui_models.models

import androidx.lifecycle.LiveData
import com.preciado.todo.core.ui_models.interfaces.IListVM
import kotlinx.coroutines.flow.Flow

abstract class ListVM<T>: VM<T>(), IListVM<T>{
    private var _selectedItem: LiveData<T>? = null
    override var selectedItem: LiveData<T>?
        get() = _selectedItem
        set(value) {
            _selectedItem = value
        }

    abstract override fun loadList(vararg args: Any): Flow<List<T>?>
    abstract override fun updateList()
    abstract override fun onItemSelected(item: T)
}