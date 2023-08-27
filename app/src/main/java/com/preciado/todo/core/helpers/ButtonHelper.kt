package com.preciado.todo.core.helpers

import androidx.lifecycle.MutableLiveData

class ButtonHelper {
    fun toggleEnablers(b1: MutableLiveData<Boolean>, b2: MutableLiveData<Boolean>){
        b1.value = b1.value != true
        b2.value = b2.value == false
    }
}