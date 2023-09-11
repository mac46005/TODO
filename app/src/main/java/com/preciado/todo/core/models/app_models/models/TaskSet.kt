package com.preciado.todo.core.models.app_models.models

import com.preciado.todo.core.models.app_models.interfaces.ITaskSet

data class TaskSet(
    override var id: Int = 0,
    override var name: String = ""
): ITaskSet<Int>