package com.preciado.todo.core.models.vm_models.interfaces

import androidx.lifecycle.LiveData

interface ISelectableItemComponent<T>: IItemComponent<T> {
    var itemSelected: LiveData<Boolean>
    fun itemSelected()
}