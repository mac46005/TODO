package com.preciado.todo.core.models.vm_models.interfaces

import androidx.lifecycle.ViewModel

interface IItemComponent<T>{
    val data: T
}