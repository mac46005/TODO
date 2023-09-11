package com.preciado.todo.core.models.app_models.models

import com.preciado.todo.core.models.app_models.interfaces.ILog
import com.preciado.todo.core.models.app_models.interfaces.IModel
import com.preciado.todo.core.models.app_models.interfaces.ITask
import com.preciado.todo.core.models.app_models.interfaces.ITaskSet
import java.time.LocalDateTime

class Log(
    override var id: Int,
    override var details: String,
    override var createdOn: LocalDateTime,
    override var task: IModel<ITask<Int, Int>, Int>,
    override var taskSet: IModel<ITaskSet<Int>, Int>,
) : ILog<Int, Int, Int> {
    override fun toString(): String {
        return "Log{id: $id, ${taskSet}, ${task}"
    }
}