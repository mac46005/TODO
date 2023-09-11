package com.preciado.todo.core.models.app_models.models

import com.preciado.todo.core.models.app_models.interfaces.IModel
import com.preciado.todo.core.models.app_models.interfaces.ITask
import com.preciado.todo.core.models.app_models.interfaces.ITaskSet
import java.time.LocalDateTime

data class Task(
    override var id: Int = 0,
    override var taskSetId: IModel<ITaskSet<Int>, Int> = TaskSet(id = 0),
    override var name: String = "",
    override var details: String = "",
    override var createdOn: LocalDateTime = LocalDateTime.now(),
    override var isCompleted: Boolean = false,
    override var completedOn: LocalDateTime? = null,
    override var dueOn: LocalDateTime? = null,
    override var taskFrequency: TaskFrequency = TaskFrequency.None
): ITask<Int,Int>
