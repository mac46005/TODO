package com.preciado.todo.core.models.app_models.models

import com.preciado.todo.core.models.app_models.interfaces.ITask
import java.time.LocalDateTime

data class Task(
    override var id: Int = 0,
    var taskSetId: Int = 0,
    override var name: String = "",
    override var details: String = "",
    override var createdOn: LocalDateTime = LocalDateTime.now(),
    override var isCompleted: Boolean = false,
    override var completedOn: LocalDateTime? = null,
    override var dueOn: LocalDateTime? = null,
    override var taskFrequency: TaskFrequency = TaskFrequency.None
): ITask<Int>
