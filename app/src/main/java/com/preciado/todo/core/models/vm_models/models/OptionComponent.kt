package com.preciado.todo.core.models.vm_models.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.preciado.todo.core.models.vm_models.interfaces.IOptionComponent

abstract class OptionComponent<T>: IOptionComponent<T> {
    var _checked: MutableLiveData<Boolean> = MutableLiveData(false)
    override var checked: LiveData<Boolean> = _checked
}