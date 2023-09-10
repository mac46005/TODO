package com.preciado.todo.core.models.app_models

import java.time.LocalDateTime

data class Task(
    var id: Int = 0,
    var taskSet_Id: Int = 0,
    var taskName: String = "",
    var details: String = "",
    var createdOn: LocalDateTime = LocalDateTime.now(),
    var isCompleted: Boolean = false,
    var completedOn: LocalDateTime? = null,
    var dueOn: LocalDateTime? = null,
    var frequency: TimeFrequency = TimeFrequency.None
)
