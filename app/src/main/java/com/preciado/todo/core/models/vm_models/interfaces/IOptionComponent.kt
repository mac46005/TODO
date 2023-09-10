package com.preciado.todo.core.models.vm_models.interfaces

import androidx.lifecycle.LiveData

interface IOptionComponent<T> : IItemComponent<T> {
    var checked: LiveData<Boolean>
    fun onItemChecked(checked: Boolean)
}