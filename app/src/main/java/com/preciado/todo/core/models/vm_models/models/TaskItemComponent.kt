package com.preciado.todo.core.models.vm_models.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.preciado.todo.core.models.vm_models.interfaces.ITaskItemComponent

abstract class TaskItemComponent<T> : ITaskItemComponent<T>, ViewModel(){

    protected var _checked: MutableLiveData<Boolean> = MutableLiveData(false)
    override var checked: LiveData<Boolean> = _checked

    override fun onItemChecked(checked: Boolean) {
        if(_checked.value == false){
            _checked.value = true
        }else{
            _checked.value = false
        }
    }

}