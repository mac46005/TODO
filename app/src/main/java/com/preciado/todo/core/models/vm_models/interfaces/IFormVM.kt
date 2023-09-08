package com.preciado.todo.core.models.vm_models.interfaces

import com.preciado.todo.data.CRUD_Operation

interface IFormVM<T>: IVM<T> {
    var crudOperation: CRUD_Operation
    fun submitForm()
}