package com.preciado.todo.core.models.app_models.models

import com.preciado.todo.core.models.app_models.interfaces.ILog
import com.preciado.todo.core.models.app_models.interfaces.IModel
import com.preciado.todo.core.models.app_models.interfaces.ITask
import com.preciado.todo.core.models.app_models.interfaces.ITaskSet
import java.time.LocalDateTime

class Log(
    override var createdOn: LocalDateTime,
    override var details: String,
    override var taskId: IModel<ITask<Int, Int>, Int>,
    override var taskSetId: IModel<ITaskSet<Int>, Int>,
    override var id: Int
) : ILog<Int, Int, Int> {
}