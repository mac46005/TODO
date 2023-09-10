package com.preciado.todo.core.models.vm_models.interfaces

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

interface IClickableItemComponent<T>: IItemComponent<T> {
    var isSelected: LiveData<Boolean>
    fun itemClicked(onClicked: () -> Unit)
}