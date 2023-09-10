package com.preciado.todo.core.models

import com.preciado.todo.core.models.app_models.TimeFrequency
import java.time.LocalDateTime

interface ITask {
    var id: Int
    var taskSetId: Int
    var taskName: String
    var details: String
    var createdOn: String
    var isCompleted: Boolean
    var completedOn: LocalDateTime?
    var frequency: TimeFrequency
}