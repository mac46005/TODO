package com.preciado.todo.core.models.vm_models.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.preciado.todo.core.models.vm_models.interfaces.ITaskItemComponent

abstract class TaskItemComponent<T> : ITaskItemComponent<T>, ViewModel(){
}