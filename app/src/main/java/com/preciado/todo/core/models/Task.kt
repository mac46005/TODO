package com.preciado.todo.core.models

data class Task(
    var id: Int = 0,
    var todoList_id: Int = 0,
    var taskName: String = "",
    var details: String = "",
    var isCompleted: Boolean = false
)
