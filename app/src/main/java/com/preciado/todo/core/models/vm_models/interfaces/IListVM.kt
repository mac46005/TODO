package com.preciado.todo.core.models.vm_models.interfaces

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

interface IListVM<T> : IVM<T> {
    var listInfo: Map<String, Any>?
    var selectedItem: LiveData<T>?
    fun <U> setItem(item: U)
    fun loadList(vararg args: Any): Flow<List<T>?>
    fun updateList()
    fun onItemSelected(item: T)
}