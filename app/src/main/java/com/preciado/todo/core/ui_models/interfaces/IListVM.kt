package com.preciado.todo.core.ui_models.interfaces

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

interface IListVM<T> : IVM<T> {
    var selectedItem: LiveData<T>?
    fun loadList(vararg args: Any): Flow<List<T>?>
    fun updateList()
    fun onItemSelected(item: T)
}