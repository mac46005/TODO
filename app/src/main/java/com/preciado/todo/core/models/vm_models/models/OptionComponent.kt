package com.preciado.todo.core.models.vm_models.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.preciado.todo.core.models.vm_models.interfaces.IOptionComponent

abstract class OptionComponent: IOptionComponent {
    protected var _itemSelected: MutableLiveData<Boolean> = MutableLiveData(false)
    override var itemSelected: LiveData<Boolean> = _itemSelected
}