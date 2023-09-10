package com.preciado.todo.core.models.vm_models.interfaces

import androidx.lifecycle.MutableLiveData

interface ITaskItemComponent<T> : IClickableItemComponent<T>, IOptionComponent<T>