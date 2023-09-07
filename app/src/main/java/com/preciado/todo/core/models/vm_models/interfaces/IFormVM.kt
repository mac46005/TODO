package com.preciado.todo.core.models.vm_models.interfaces

import com.preciado.todo.data.CRUDEnum

interface IFormVM<T>: IVM<T> {
    var crudEnum: CRUDEnum
    fun submitForm()
}