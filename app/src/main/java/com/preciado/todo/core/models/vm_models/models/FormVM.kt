package com.preciado.todo.core.models.vm_models.models

import com.preciado.todo.core.models.vm_models.interfaces.IFormVM
import com.preciado.todo.data.CRUD_Operation
import javax.inject.Inject


abstract class FormVM<T> : IFormVM<T>, VM<T>() {

    private var _crudOperation: CRUD_Operation = CRUD_Operation.CREATE
    override var crudOperation: CRUD_Operation
        get() = _crudOperation
        set(value){
            _crudOperation = value
        }
}