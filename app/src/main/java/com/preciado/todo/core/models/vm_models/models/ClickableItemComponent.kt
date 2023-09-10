package com.preciado.todo.core.models.vm_models.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.preciado.todo.core.models.vm_models.interfaces.IClickableItemComponent

abstract class ClickableItemComponent<T> : IClickableItemComponent<T> {
    protected var _isSelected: MutableLiveData<Boolean> = MutableLiveData(false)
    override var isSelected: LiveData<Boolean> = _isSelected
}